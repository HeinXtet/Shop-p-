package com.heinhtet.deevd.shop.dataservice
import android.arch.lifecycle.LiveData
import com.heinhtet.deevd.shop.base.AppDatabase
import com.heinhtet.deevd.shop.helper.DbCallback
import com.heinhtet.deevd.shop.helper.RxThread
import com.heinhtet.deevd.shop.model.dao.ProductDao
import com.heinhtet.deevd.shop.model.entity.Products
import io.reactivex.Observable
import java.util.*

/**
 * Created by Hein Htet on 8/12/18.

 */

class ProductDataService(db: AppDatabase){

    private var productDao : ProductDao = db.productDao()
    private var productList : LiveData<List<Products>>
    private lateinit var dbCallback : DbCallback

    init {
        productList  = productDao.getAll()
    }

    fun getProducts() : LiveData<List<Products>>{
        return productList
    }


    fun insertProduct(products: Products,dbCallback: DbCallback) {
        if (!checkProductIsExist(products.productName)){
            insert(products,callback = dbCallback)
        }
    }

    fun insert(products: Products,callback: DbCallback){
        Observable.just(products)
                .compose(RxThread.applyAsyncObservable())
                .subscribe({success->
                    callback.insertFinish()
                })
    }

    fun checkProductIsExist(name: String) :Boolean{
        var isExist = false
        productDao.loadAllByNames(name)
                .compose(RxThread.applyAsync())
                .subscribe({it-> isExist = it.isEmpty()})
        return isExist
    }


}