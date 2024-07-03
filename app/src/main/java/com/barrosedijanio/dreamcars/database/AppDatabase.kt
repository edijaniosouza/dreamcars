package com.barrosedijanio.dreamcars.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barrosedijanio.dreamcars.database.dao.CarsDao
import com.barrosedijanio.dreamcars.database.dao.FavoriteCarsDao
import com.barrosedijanio.dreamcars.database.dao.UserDao
import com.barrosedijanio.dreamcars.models.Car
import com.barrosedijanio.dreamcars.models.FavoriteCar
import com.barrosedijanio.dreamcars.models.User

@Database(entities = [User::class, Car::class, FavoriteCar::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun carsDao() : CarsDao
    abstract fun favoriteCarsDao() : FavoriteCarsDao
}