package com.upb.examen2.data

import com.upb.examen2.data.remote.PerrosApiService
import com.upb.examen2.data.remote.RetrofitClient
import com.upb.examen2.model.Raza
import com.upb.examen2.util.Resultado
import com.upb.examen2.util.ejecutarLlamada

class PerrosRepository(
    private val api: PerrosApiService = RetrofitClient.apiPerros
) {

    suspend fun obtenerRazas(): Resultado<List<Raza>> =
        ejecutarLlamada { api.obtenerRazas().aRazas() }
}