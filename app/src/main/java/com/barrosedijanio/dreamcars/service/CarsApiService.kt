package com.barrosedijanio.dreamcars.service

import com.barrosedijanio.dreamcars.database.model.FavoriteCarList
import com.barrosedijanio.dreamcars.service.model.Cars
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarsApiService {

    @GET("/cars.json")
    suspend fun getData() : Response<Cars>

    @POST("/cars/leads")
    suspend fun postLeads(@Body body: FavoriteCarList): Response<Unit>
}