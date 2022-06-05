package com.example.nasadata.model.pojo

import com.squareup.moshi.Json

data class NasaDataItem(
    @Json(name = "description")
    val description: String,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "apod_site")
    val apodSite: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "media_type")
    val mediaType: String,
    @Json(name = "hdurl")
    val hdurl: String,
)