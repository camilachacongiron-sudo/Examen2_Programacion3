package com.upb.examen2.data.remote

import retrofit2.http.GET

interface PerrosApiService {

    @GET("breeds/list/all")
    suspend fun obtenerRazas(): RazasResponse
}