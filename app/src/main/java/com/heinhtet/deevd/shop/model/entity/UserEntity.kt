package com.heinhtet.deevd.shop.model.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey



/**
 * Created by Hein Htet on 8/12/18.
 */

@Entity(tableName = "user")
class  UserEntity{
    @PrimaryKey
    var uid: Int = 0

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var lastName: String? = null
}