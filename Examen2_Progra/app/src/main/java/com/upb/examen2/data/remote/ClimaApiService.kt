package com.upb.examen2.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaApiService {

    @GET("forecast")
    suspend fun obtenerClima(
        @Query("latitude") latitud: Double,
        @Query("longitude") longitud: Double,
        @Query("current") actual: String,
        @Query("timezone") zonaHoraria: String
    ): ClimaResponse
}