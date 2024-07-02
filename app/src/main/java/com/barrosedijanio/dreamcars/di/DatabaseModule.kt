package com.barrosedijanio.dreamcars.di

import androidx.room.Room
import com.barrosedijanio.dreamcars.database.AppDatabase
import com.barrosedijanio.dreamcars.database.dao.CarsDao
import com.barrosedijanio.dreamcars.database.dao.FavoriteCarsDao
import com.barrosedijanio.dreamcars.database.dao.UserDao
import com.barrosedijanio.dreamcars.database.repositories.DatabaseRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, name = "dreamcars")
            .fallbackToDestructiveMigration().build()
    }

    single<UserDao> {
        get<AppDatabase>().userDao()
    }
    single<CarsDao> {
        get<AppDatabase>().carsDao()
    }
    single<FavoriteCarsDao> {
        get<AppDatabase>().favoriteCarsDao()
    }

    single<DatabaseRepository> {
        DatabaseRepository(get(), get(), get(), get())
    }
}