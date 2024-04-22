package com.application.handphone_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Handphone::class],
    version = 1
)
abstract class HandphoneDatabase : RoomDatabase() {

    abstract fun hpDao(): HandphoneDao

    companion object {
        @Volatile
        private var INSTANCE: HandphoneDatabase? = null

        fun getInstance(context: Context): HandphoneDatabase {
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HandphoneDatabase::class.java,
                    "hp_db"
                ).build()
                INSTANCE = instance
                instance
            }
            return INSTANCE as HandphoneDatabase
        }
    }
}