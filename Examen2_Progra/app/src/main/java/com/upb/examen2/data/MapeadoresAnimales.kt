package com.upb.examen2.data

import com.upb.examen2.data.local.RazaDatoEntity
import com.upb.examen2.data.remote.DatoGatoDto
import com.upb.examen2.data.remote.RazasResponse
import com.upb.examen2.model.DatoGato
import com.upb.examen2.model.Raza
import com.upb.examen2.model.RazaDato

fun RazasResponse.aRazas(): List<Raza> =
    message.map { (nombre, subrazas) -> Raza(nombre = nombre, subrazas = subrazas) }

fun DatoGatoDto.aDatoGato(): DatoGato =
    DatoGato(texto = fact, longitud = length)

// COMBINADO: Perros + Gatos -> una entidad
fun Raza.combinarRazaConDato(dato: DatoGato): RazaDatoEntity =
    RazaDatoEntity(
        raza = nombre,
        subrazas = subrazas.joinToString(", "),
        datoGato = dato.texto
    )

fun RazaDatoEntity.aRazaDato(): RazaDato =
    RazaDato(
        id = id,
        raza = raza,
        subrazas = if (subrazas.isBlank()) emptyList() else subrazas.split(", "),
        datoGato = datoGato
    )