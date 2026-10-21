package com.upb.examen2.data

import com.upb.examen2.data.remote.GatosApiService
import com.upb.examen2.data.remote.RetrofitClient
import com.upb.examen2.model.DatoGato
import com.upb.examen2.util.Resultado
import com.upb.examen2.util.ejecutarLlamada

class GatosRepository(
    private val api: GatosApiService = RetrofitClient.apiGatos
) {

    suspend fun obtenerDatos(limite: Int = 10): Resultado<List<DatoGato>> =
        ejecutarLlamada { api.obtenerDatos(limite).aDatosGato() }
}