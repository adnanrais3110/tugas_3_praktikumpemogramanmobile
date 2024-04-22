package com.application.handphone_app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HandphoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHp(handphone: Handphone)

    @Query("SELECT * FROM hp WHERE id = :id")
    fun getHp(id: Int): Handphone

    @Query("SELECT * FROM hp")
    fun getAllHandphones(): List<Handphone>

    @Delete
    fun deleteHp(handphone: Handphone)

    @Query("UPDATE hp SET hpName = :hpName, brand = :brand, hpClass = :hpClass, price = :price WHERE id = :id")
    fun updateHp(id: Int, hpName: String, brand: String, hpClass: String, price: String)
}