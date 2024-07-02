package com.barrosedijanio.dreamcars.di

import com.barrosedijanio.dreamcars.service.CarsApiService
import com.barrosedijanio.dreamcars.service.repositories.ServiceRepository
import com.barrosedijanio.dreamcars.worker.UploadFavCars
import com.google.gson.GsonBuilder
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single {
        val gson = GsonBuilder().create()
        Retrofit.Builder()
            .baseUrl("https://wswork.com.br")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CarsApiService::class.java)
    }

    single <ServiceRepository>{
        ServiceRepository(get(), get())
    }

    worker {
        UploadFavCars(get(), get(), get())
    }
}