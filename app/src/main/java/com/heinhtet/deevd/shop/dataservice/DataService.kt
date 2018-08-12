package com.heinhtet.deevd.shop.dataservice

import android.app.Application
import android.arch.lifecycle.LiveData
import com.heinhtet.deevd.shop.base.AppDatabase
import com.heinhtet.deevd.shop.model.dao.UserDao
import com.heinhtet.deevd.shop.model.entity.UserEntity
import android.os.AsyncTask
import android.util.Log
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Hein Htet on 8/12/18.
 */
class DataService(application: Application) {

    val TAG = "DataService"
    private  var mAllWords : LiveData< List<UserEntity>>

    var productDataService: ProductDataService


    var userDao : UserDao

    init {
        val db = AppDatabase.INSTANCE.DB(application)
        productDataService = ProductDataService(db)
        userDao = db.userDao()

        mAllWords = userDao.getAll()
    }
    fun getAllWords(): LiveData<List<UserEntity>> {
        return mAllWords
    }


    fun insert(user : UserEntity){
       if (!isExist(user.uid)){
           insertUser(user)
       }
    }

    inner class insertAsyncTask constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<UserEntity, Void, Void>() {
        override fun doInBackground(vararg params: UserEntity): Void? {
            mAsyncTaskDao.insertAll(params[0])
            return null
        }
    }

    private fun insertUser(user: UserEntity){
        Completable.fromAction { userDao.insertAll(user) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object :CompletableObserver{
                    override fun onComplete() {
                        Log.i(TAG,"insertComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

    private fun isExist(id:Int):Boolean{
        var isExist = false
        userDao.loadAllByIds(userIds = id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({it->
                    isExist = !it.isEmpty()
                }).dispose()
        return isExist
    }
}
