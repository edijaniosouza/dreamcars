package com.barrosedijanio.dreamcars.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barrosedijanio.dreamcars.service.model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyCars(cars: List<Car>)

    @Query("SELECT * FROM cars")
    fun getCars(): Flow<List<Car>>

}