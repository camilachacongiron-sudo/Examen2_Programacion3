package com.upb.examen2.data.remote

// "message" es un mapa: nombre de la raza -> lista de subrazas
data class RazasResponse(
    val message: Map<String, List<String>>,
    val status: String
)