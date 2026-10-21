package com.upb.examen2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clima_dato")
data class ClimaDatoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val temperatura: Double,
    val humedad: Int,
    val vientoKmh: Double,
    val hora: String,
    val datoGato: String
)