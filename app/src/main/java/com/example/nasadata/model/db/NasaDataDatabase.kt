package com.example.nasadata.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nasadata.MyApplication

@Database(entities = [NasaDataEntity::class], version = 1, exportSchema = false)
abstract class NasaDataDatabase: RoomDatabase() {
    abstract fun getNasaDataDao(): NasaDataDao

    companion object {
        private val DATABASE_NAME = "nasa_data_db"
        private var instance: NasaDataDatabase? = null
            private set

        fun getInstance(): NasaDataDatabase {
            return instance ?: synchronized(this) {
                buildDatabase()
            }
        }

        fun buildDatabase(): NasaDataDatabase {
            return Room.databaseBuilder(MyApplication.instance,
                NasaDataDatabase::class.java, DATABASE_NAME).build().also {
                    instance = it
            }
        }
    }
}