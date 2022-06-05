package com.example.nasadata.model.repository

import androidx.lifecycle.LiveData
import com.example.nasadata.model.db.NasaDataEntity
import com.example.nasadata.model.network.ApiService
import com.example.nasadata.model.network.RemoteDataSource
import com.example.nasadata.model.pojo.NasaDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NasaDataRepository: BaseRepository() {
    private val apiService by lazy { RemoteDataSource().buildApi(ApiService::class.java) }

    suspend fun getNasaData() = withContext(Dispatchers.IO) {
        saveNasaData(callNasaDataApi())
    }

    private suspend fun callNasaDataApi() = apiService.getNasaData()

    private fun saveNasaData(listNasaDataItem: List<NasaDataItem>) {
        val list = listNasaDataItem.map { trans2Entity(it) }.toTypedArray()
        nasaDataDao.insertNasaData(*list)
    }

    private fun trans2Entity(item: NasaDataItem): NasaDataEntity {
        return NasaDataEntity(
            description = item.description,
            copyright = item.copyright,
            title = item.title,
            url = item.url,
            apodSite = item.apodSite,
            date = item.date,
            mediaType = item.mediaType,
            hdurl = item.hdurl,
        )
    }

    fun queryAllNasaData(): LiveData<List<NasaDataEntity>> {
        return nasaDataDao.queryAllNasaData()
    }

    fun queryFavorData(): LiveData<List<NasaDataEntity>> {
        return nasaDataDao.queryFavorData()
    }
}