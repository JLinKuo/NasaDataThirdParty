package com.example.nasadata.model.repository

import com.example.nasadata.model.db.NasaDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRepository: BaseRepository() {
    suspend fun updateNasaDataItem(entity: NasaDataEntity) = withContext(Dispatchers.IO) {
        nasaDataDao.updateNasaDataItem(entity)
    }
}