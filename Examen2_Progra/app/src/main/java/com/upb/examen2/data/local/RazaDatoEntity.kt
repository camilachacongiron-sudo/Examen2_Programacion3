package com.upb.examen2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "raza_dato")
data class RazaDatoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val raza: String,
    val subrazas: String, // lista unida con ", "
    val datoGato: String
)