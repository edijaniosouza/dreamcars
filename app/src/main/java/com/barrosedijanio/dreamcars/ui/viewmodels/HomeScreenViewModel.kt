package com.barrosedijanio.dreamcars.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.dreamcars.repositories.CacheRepository
import com.barrosedijanio.dreamcars.models.FavoriteCar
import com.barrosedijanio.dreamcars.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.ui.navigation.Session
import com.barrosedijanio.dreamcars.models.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val cacheRepository: CacheRepository,
    private val databaseRepository: DatabaseRepository,
    private val session: Session
) : ViewModel() {

    private val _data = MutableStateFlow<List<Car>>(emptyList())
    val data = _data.asStateFlow()

    private val _favoriteData = MutableStateFlow<List<FavoriteCar>>(emptyList())
    val favoriteData = _favoriteData.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            cacheRepository.getData().collect { cars ->
                if (cars != null) {
                    _data.value = cars
                }
                launch {
                    databaseRepository.getFavoriteCars().collect { favoriteCars ->
                        _favoriteData.value = favoriteCars
                    }
                }
            }
        }
    }

    fun toggleFavorite(carSelected: Car, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                databaseRepository.deleteFavoriteCar(carSelected.id)
            } else {
                session.getUserId().collect { userId ->
                    databaseRepository.getUserById(userId).collect {
                        val liked =
                            FavoriteCar(
                                userId = userId,
                                userEmail = it!!.email,
                                carId = carSelected.id
                            )
                        databaseRepository.addFavoriteCar(liked)
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            session.setUserLogin(false)
        }
    }
}