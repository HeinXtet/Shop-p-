package com.heinhtet.deevd.shop.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.heinhtet.deevd.shop.model.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


/**
 * Created by Hein Htet on 8/12/18.
 */
@Dao
 interface  UserDao{
    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: Int): Single<List<UserEntity>>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): UserEntity

    @Insert
    fun insertAll(users: UserEntity)

    @Delete
    fun delete(user: UserEntity)

}