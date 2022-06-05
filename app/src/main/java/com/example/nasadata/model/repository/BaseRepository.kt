package com.example.nasadata.model.repository

import com.example.nasadata.model.db.NasaDataDatabase

abstract class BaseRepository {
    protected var TAG = javaClass.simpleName

    private val nasaDataDatabase by lazy { NasaDataDatabase.getInstance() }
    val nasaDataDao by lazy { nasaDataDatabase.getNasaDataDao() }
}