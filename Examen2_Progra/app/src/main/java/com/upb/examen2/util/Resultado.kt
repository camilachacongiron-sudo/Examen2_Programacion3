package com.upb.examen2.util

/** Resultado de una operacion que puede fallar (por ejemplo, una llamada de red). */
sealed class Resultado<out T> {
    data class Exito<out T>(val datos: T) : Resultado<T>()
    data class Error(val mensaje: String) : Resultado<Nothing>()
}
