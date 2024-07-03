package com.barrosedijanio.dreamcars.models

import com.google.gson.annotations.SerializedName


data class FavoriteCarList(
    @SerializedName("wanted_cars")
    val wanted : List<FavoriteCar>
)
