package com.heinhtet.deevd.shop.base

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.heinhtet.deevd.shop.model.entity.UserEntity
import android.arch.persistence.room.Room
import android.content.Context
import com.heinhtet.deevd.shop.model.dao.ProductDao
import com.heinhtet.deevd.shop.model.dao.UserDao
import com.heinhtet.deevd.shop.model.entity.Products


/**
 * Created by Hein Htet on 8/12/18.
 */
@Database(entities = arrayOf(UserEntity::class, Products::class) , version = 1)
abstract  class  AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

    object  INSTANCE {
        private var INSTANCE: AppDatabase? = null
        fun DB(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, "user_database")
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}