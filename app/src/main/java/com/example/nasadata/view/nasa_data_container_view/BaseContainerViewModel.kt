package com.example.nasadata.view.nasa_data_container_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.nasadata.model.db.NasaDataEntity
import com.example.nasadata.model.repository.NasaDataRepository
import com.example.nasadata.view.base.BaseViewModel
import kotlinx.coroutines.launch

class BaseContainerViewModel: BaseViewModel() {
    private val repository by lazy { NasaDataRepository() }

    var cntAllData = 0
    var cntFavor = 0

    val queryAllNasaDataResult: LiveData<List<NasaDataEntity>>
        get() = repository.queryAllNasaData()

    fun queryAllNasaData() {
        viewModelScope.launch {
            repository.queryAllNasaData()
        }
    }

    fun reachRemoteNasaData() {
        viewModelScope.launch {
            repository.getNasaData()
        }
    }

    val queryFavorDataResult: LiveData<List<NasaDataEntity>>
        get() = repository.queryFavorData()

    fun queryFavorData() {
        viewModelScope.launch {
            repository.queryFavorData()
        }
    }
}