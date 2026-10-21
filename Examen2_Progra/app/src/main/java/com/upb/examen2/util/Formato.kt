package com.upb.examen2.util

/** 25 -> "#025" */
fun formatearNumero(id: Int): String = "#" + id.toString().padStart(3, '0')

/** "mr-mime" -> "Mr Mime" */
fun capitalizar(texto: String): String =
    texto.replace('-', ' ')
        .split(' ')
        .joinToString(" ") { palabra -> palabra.replaceFirstChar { it.uppercase() } }
