package com.upb.examen2.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GatosApiService {

    @GET("facts")
    suspend fun obtenerDatos(
        @Query("limit") limite: Int
    ): DatosGatosResponse
}