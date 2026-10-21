package com.upb.examen2.data.remote

data class DatosGatosResponse(
    val data: List<DatoGatoDto>
)

data class DatoGatoDto(
    val fact: String,
    val length: Int
)