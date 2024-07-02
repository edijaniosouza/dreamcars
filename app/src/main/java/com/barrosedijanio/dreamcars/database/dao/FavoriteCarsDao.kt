package com.barrosedijanio.dreamcars.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.barrosedijanio.dreamcars.database.model.FavoriteCar
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCar(liked: FavoriteCar)

    @Query("SELECT * FROM favorite_cars WHERE user_id = :userId")
    fun getFavorites(userId: Int): Flow<List<FavoriteCar>>

    @Query("SELECT * FROM favorite_cars WHERE car_id = :carId")
    fun getFavoriteCar(carId: Int): Flow<FavoriteCar?>

    @Delete
    suspend fun deleteFavoriteCar(liked: FavoriteCar)
}