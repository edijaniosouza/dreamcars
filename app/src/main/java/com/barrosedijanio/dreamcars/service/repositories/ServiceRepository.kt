package com.barrosedijanio.dreamcars.service.repositories

import android.util.Log
import com.barrosedijanio.dreamcars.core.generic.GenericResult
import com.barrosedijanio.dreamcars.database.model.FavoriteCarList
import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.service.CarsApiService
import com.barrosedijanio.dreamcars.service.model.Cars
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
            Log.i("testeLeads", "postLeads - favoritos: $favoriteCars ")

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
            Log.e("testeLeads", "postLeads ERRO: $e")
            return GenericResult.Error(0)
        }
    }
}