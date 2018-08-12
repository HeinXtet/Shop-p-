package com.heinhtet.deevd.shop.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.heinhtet.deevd.shop.model.entity.Products
import com.heinhtet.deevd.shop.model.entity.UserEntity
import io.reactivex.Single

/**
 * Created by Hein Htet on 8/12/18.
 */

@Dao
interface ProductDao{

    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Products>>

    @Query("SELECT * FROM product WHERE productId IN (:productId)")
    fun loadAllByIds(productId: Long): Single<List<Products>>

    @Query("SELECT * FROM product WHERE product_name IN (:productName)")
    fun loadAllByNames (productName : String) : Single<List<Products>>
    @Insert
    fun insertAll(users: Products)

    @Delete
    fun delete(user: Products)
}