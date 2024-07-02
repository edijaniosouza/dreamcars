package com.barrosedijanio.dreamcars.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.dreamcars.cache.CacheRepository
import com.barrosedijanio.dreamcars.database.model.FavoriteCar
import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.service.model.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val cacheRepository: CacheRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _data = MutableStateFlow<List<Car>>(emptyList())
    val data = _data.asStateFlow()

    private val _favoriteData = MutableStateFlow<List<FavoriteCar>>(emptyList())
    val favoriteData = _favoriteData.asStateFlow()

    private val userId = databaseRepository.userId

    fun loadData() {
        viewModelScope.launch {
            cacheRepository.getData().collect { dataList ->
                if (dataList != null) {
                    _data.value = dataList
                }

                launch {
                    databaseRepository.getFavoriteCars().collect { favoriteCars ->
                        _favoriteData.value = favoriteCars
                    }
                }
            }
        }
    }

    fun favoriteItem(carSelected: Car, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                databaseRepository.deleteFavoriteCar(carSelected.id)
            } else {
                val userIdCollected = userId.first()
                Log.i("testeFavorite", "favoriteItem: not ready yet $userIdCollected")

                val liked = FavoriteCar(userId = userIdCollected, carId = carSelected.id)
                databaseRepository.addFavoriteCar(liked)
            }
        }
    }
}