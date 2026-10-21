package com.upb.examen2.data

import com.upb.examen2.data.local.ClimaDatoDao
import com.upb.examen2.model.ClimaDato
import com.upb.examen2.util.Resultado
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClimaDatoRepository(
    private val dao: ClimaDatoDao,
    private val climaRepositorio: ClimaRepository = ClimaRepository(),
    private val gatosRepositorio: GatosRepository = GatosRepository()
) {

    fun observarTodos(): Flow<List<ClimaDato>> =
        dao.observarTodos().map { entidades -> entidades.map { it.aClimaDato() } }

    /** Pide clima y un dato de gato a las APIs, los combina y guarda el registro en Room. */
    suspend fun sincronizar(): Resultado<Unit> {
        val clima = when (val r = climaRepositorio.obtenerClima()) {
            is Resultado.Exito -> r.datos
            is Resultado.Error -> return Resultado.Error(r.mensaje)
        }
        val dato = when (val r = gatosRepositorio.obtenerDatos(1)) {
            is Resultado.Exito -> r.datos.firstOrNull()
                ?: return Resultado.Error("La API de gatos no devolvió datos.")
            is Resultado.Error -> return Resultado.Error(r.mensaje)
        }

        dao.insertar(clima.combinarClimaConDato(dato))
        return Resultado.Exito(Unit)
    }

    suspend fun eliminar(id: Int) = dao.eliminarPorId(id)
}