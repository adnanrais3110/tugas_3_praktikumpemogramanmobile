package com.application.handphone_app.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hp")
data class Handphone(
    @ColumnInfo(name = "hpName")
    var hpName: String,

    @ColumnInfo(name = "brand")
    var brand: String,

    @ColumnInfo(name = "hpClass")
    var hpClass: String,

    @ColumnInfo(name = "price")
    var price: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)