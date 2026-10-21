package com.upb.examen2.data

import com.upb.examen2.data.remote.ClimaApiService
import com.upb.examen2.data.remote.RetrofitClient
import com.upb.examen2.model.Clima
import com.upb.examen2.util.Resultado
import com.upb.examen2.util.ejecutarLlamada

class ClimaRepository(
    private val api: ClimaApiService = RetrofitClient.apiClima
) {

    // Por defecto: La Paz
    suspend fun obtenerClima(
        latitud: Double = -16.5,
        longitud: Double = -68.15
    ): Resultado<Clima> = ejecutarLlamada {
        api.obtenerClima(
            latitud = latitud,
            longitud = longitud,
            actual = "temperature_2m,relative_humidity_2m,wind_speed_10m",
            zonaHoraria = "America/La_Paz"
        ).aClima()
    }
}