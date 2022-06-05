package com.example.nasadata.model.network

import com.example.nasadata.model.pojo.NasaDataItem
import retrofit2.http.GET

interface ApiService {
    @GET("main/apod.json")
    suspend fun getNasaData(): List<NasaDataItem>
}