package com.example.nasadata.view.detail

import androidx.lifecycle.*
import com.example.nasadata.model.db.NasaDataEntity
import com.example.nasadata.model.repository.DetailRepository
import com.example.nasadata.view.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailViewModel: BaseViewModel() {

    private val repository by lazy { DetailRepository() }

    lateinit var entity: NasaDataEntity

    private val _updateFavorResult = MutableLiveData<Int>()
    val updateFavorResult: LiveData<Int> = _updateFavorResult.map {
        if(it == 0) {
            // 寫入資料庫不成功，故恢復原狀態
            entity.isFavorite = !entity.isFavorite
        }
        it
    }

    fun modifyFavorState(entity: NasaDataEntity) {
        viewModelScope.launch {
            entity.isFavorite = !entity.isFavorite
            _updateFavorResult.value = repository.updateNasaDataItem(entity)
        }
    }
}