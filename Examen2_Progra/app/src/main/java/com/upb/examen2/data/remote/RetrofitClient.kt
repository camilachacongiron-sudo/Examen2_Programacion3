package com.upb.examen2.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Cada URL base debe terminar en "/".
    private const val URL_CLIMA = "https://api.open-meteo.com/v1/"
    private const val URL_PERROS = "https://dog.ceo/api/"
    private const val URL_GATOS = "https://catfact.ninja/"

    private fun crearRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiClima: ClimaApiService by lazy {
        crearRetrofit(URL_CLIMA).create(ClimaApiService::class.java)
    }

    val apiPerros: PerrosApiService by lazy {
        crearRetrofit(URL_PERROS).create(PerrosApiService::class.java)
    }

    val apiGatos: GatosApiService by lazy {
        crearRetrofit(URL_GATOS).create(GatosApiService::class.java)
    }
}
