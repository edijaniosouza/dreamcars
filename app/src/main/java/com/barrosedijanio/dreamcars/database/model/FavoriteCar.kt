package com.barrosedijanio.dreamcars.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.barrosedijanio.dreamcars.service.model.Car
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_cars", foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Car::class,
        parentColumns = ["id"],
        childColumns = ["car_id"],
        onDelete = ForeignKey.CASCADE
    )
])
data class FavoriteCar(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    val userId: Int,

    @ColumnInfo(name = "car_id")
    @SerializedName("car_id")
    val carId: Int,
)
