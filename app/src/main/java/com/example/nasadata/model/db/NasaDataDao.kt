package com.example.nasadata.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao    // Database access object
interface NasaDataDao {
    @Insert
    fun insertNasaData(vararg entities: NasaDataEntity)

    @Query("SELECT * FROM nasa_data_table ORDER BY id DESC")
    fun queryAllNasaData(): LiveData<List<NasaDataEntity>>

    @Query("SELECT * FROM nasa_data_table WHERE isFavorite == 1 ORDER BY id DESC")
    fun queryFavorData(): LiveData<List<NasaDataEntity>>

    @Update
    fun updateNasaDataItem(entity: NasaDataEntity): Int
}
