package com.heinhtet.deevd.shop.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Hein Htet on 8/12/18.
 */

@Entity(tableName = "product")
class Products {

    @PrimaryKey(autoGenerate = true)
    var productId : Long = 0L

    @ColumnInfo(name = "product_name")
    var productName: String = ""

    @ColumnInfo(name = "buying_price")
    var buyingPrice : Double = 0.0

    @ColumnInfo(name = "selling_price")
    var sellingPrice : Double = 0.0


}