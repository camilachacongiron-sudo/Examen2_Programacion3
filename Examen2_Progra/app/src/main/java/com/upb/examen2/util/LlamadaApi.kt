package com.upb.examen2.util

import java.io.IOException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

suspend fun <T> ejecutarLlamada(llamada: suspend () -> T): Resultado<T> =
    try {
        Resultado.Exito(llamada())
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        Resultado.Error("El servidor respondió con un error (código ${e.code()}).")
    } catch (e: IOException) {
        Resultado.Error("No hay conexión a internet. Revisa tu red e inténtalo de nuevo.")
    } catch (e: Exception) {
        Resultado.Error("Ocurrió un error inesperado: ${e.localizedMessage ?: "desconocido"}")
    }