package com.example.nasadata.model.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "nasa_data_table")
data class NasaDataEntity(
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "copyright")
    val copyright: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "apod_site")
    val apodSite: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "mediaType")
    val mediaType: String,
    @ColumnInfo(name = "hdurl")
    val hdurl: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
): Parcelable
