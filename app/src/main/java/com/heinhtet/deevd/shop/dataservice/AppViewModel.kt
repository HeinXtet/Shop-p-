package com.heinhtet.deevd.shop.dataservice

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import com.heinhtet.deevd.shop.model.entity.Products
import com.heinhtet.deevd.shop.model.entity.UserEntity


/**
 * Created by Hein Htet on 8/12/18.
 */

class AppViewModel(app: Application) : AndroidViewModel(app){
    private var dataService: DataService = DataService(app)
    private var mAllWords: LiveData<List<UserEntity>>
    private var products: LiveData<List<Products>>



    init {
        mAllWords = dataService.getAllWords()
        products = dataService.productDataService.getProducts()
    }


    fun getAllWords(): LiveData<List<UserEntity>> {
        return mAllWords
    }

    fun getAllProduct():LiveData<List<Products>>{
        return products
    }

    fun insertUser(entity: UserEntity){
        dataService.insert(entity)
    }


}