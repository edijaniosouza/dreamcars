package com.barrosedijanio.dreamcars.cache

import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import com.barrosedijanio.dreamcars.service.model.Car
import com.barrosedijanio.dreamcars.service.repositories.ServiceRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

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
                    carsUpdated.cars.forEach {
                        val alreadyInCache = cachedData.contains(it)
                        if(!alreadyInCache) databaseRepository.insertCar(it)
                    }
                }
            }
        } catch (e: Exception) {
            emit(null)
        }
    }
}