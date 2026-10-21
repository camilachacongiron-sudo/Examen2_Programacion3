package com.upb.examen2.data.remote

import com.google.gson.annotations.SerializedName

data class ClimaResponse(
    val latitude: Double,
    val longitude: Double,
    val current: ClimaActualDto
)

data class ClimaActualDto(
    val time: String,
    @SerializedName("temperature_2m") val temperatura: Double,
    @SerializedName("relative_humidity_2m") val humedad: Int,
    @SerializedName("wind_speed_10m") val viento: Double
)