package com.barrosedijanio.dreamcars.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val cor: String,
    val ano: Int,
    val combustivel: String,
    val modelo_id: Int,
    val nome_modelo: String,
    val num_portas: Int,
    val timestamp_cadastro: Long,
    val valor: Double
)