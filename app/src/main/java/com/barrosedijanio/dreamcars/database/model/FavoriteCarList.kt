package com.barrosedijanio.dreamcars.database.model

import com.google.gson.annotations.SerializedName


data class FavoriteCarList(
    @SerializedName("wanted_cars")
    val wanted : List<FavoriteCar>
)
