package com.barrosedijanio.dreamcars.repositories

import android.util.Log
import com.barrosedijanio.dreamcars.models.Car
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class CacheRepository(
    private val serviceRepository: ServiceRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend fun getData() = flow<List<Car>?> {
        try {
            val cachedData = databaseRepository.getCarData().first()
            emit(cachedData)

            val request = serviceRepository.getData()
            if (request.isSuccessful) {
                request.body()?.let { carsUpdated ->
                    emit(carsUpdated.cars)
                    databaseRepository.insertCarData(carsUpdated)
                }
            }

        } catch (e: Exception) {
            Log.i("testData", "erro: ${e.message}")
            emit(null)
        }
    }
}