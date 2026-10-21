package com.upb.examen2.data

import com.upb.examen2.data.local.ClimaDatoEntity
import com.upb.examen2.data.remote.ClimaResponse
import com.upb.examen2.data.remote.DatosGatosResponse
import com.upb.examen2.model.Clima
import com.upb.examen2.model.ClimaDato
import com.upb.examen2.model.DatoGato

fun ClimaResponse.aClima(): Clima =
    Clima(
        temperatura = current.temperatura,
        humedad = current.humedad,
        vientoKmh = current.viento,
        hora = current.time
    )

fun DatosGatosResponse.aDatosGato(): List<DatoGato> =
    data.map { it.aDatoGato() }

// COMBINADO: Clima + Gatos -> una entidad
fun Clima.combinarClimaConDato(dato: DatoGato): ClimaDatoEntity =
    ClimaDatoEntity(
        temperatura = temperatura,
        humedad = humedad,
        vientoKmh = vientoKmh,
        hora = hora,
        datoGato = dato.texto
    )

fun ClimaDatoEntity.aClimaDato(): ClimaDato =
    ClimaDato(
        id = id,
        temperatura = temperatura,
        humedad = humedad,
        vientoKmh = vientoKmh,
        hora = hora,
        datoGato = datoGato
    )