package com.barrosedijanio.dreamcars.repositories

import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.models.FavoriteCarList
import com.barrosedijanio.dreamcars.service.CarsApiService
import com.barrosedijanio.dreamcars.models.Cars
import kotlinx.coroutines.flow.first
import retrofit2.Response

class ServiceRepository(
    private val databaseRepository: DatabaseRepository,
    private val apiService: CarsApiService
) {

    suspend fun getData(): Response<Cars> {
        return apiService.getData()
    }

    suspend fun postLeads() : GenericResult {
        try{
            val favoriteCars = databaseRepository.getFavoriteCars().first()

            if(favoriteCars.isNotEmpty()){
                val favoriteCarsList = FavoriteCarList(favoriteCars)
                val postLeads = apiService.postLeads(favoriteCarsList)

                return if(postLeads.code() == 200){
                    GenericResult.Success
                } else{
                    GenericResult.Error(0)
                }
            }
            return GenericResult.Error(0)
        }catch (e: Exception){
            return GenericResult.Error(0)
        }
    }
}