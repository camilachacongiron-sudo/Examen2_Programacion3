package com.upb.examen2.data

import com.upb.examen2.data.local.RazaDatoDao
import com.upb.examen2.model.RazaDato
import com.upb.examen2.util.Resultado
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RazaDatoRepository(
    private val dao: RazaDatoDao,
    private val perrosRepositorio: PerrosRepository = PerrosRepository(),
    private val gatosRepositorio: GatosRepository = GatosRepository()
) {

    fun observarTodos(): Flow<List<RazaDato>> =
        dao.observarTodos().map { entidades -> entidades.map { it.aRazaDato() } }

    /** Pide perros y gatos a las APIs, combina cada raza con un dato de gato y lo guarda en Room. */
    suspend fun sincronizar(cantidad: Int = 10): Resultado<Int> {
        val razas = when (val r = perrosRepositorio.obtenerRazas()) {
            is Resultado.Exito -> r.datos
            is Resultado.Error -> return Resultado.Error(r.mensaje)
        }
        val datos = when (val r = gatosRepositorio.obtenerDatos(cantidad)) {
            is Resultado.Exito -> r.datos
            is Resultado.Error -> return Resultado.Error(r.mensaje)
        }

        val combinados = razas.zip(datos).map { (raza, dato) -> raza.combinarRazaConDato(dato) }

        // Solo se borra lo anterior cuando las dos APIs respondieron bien.
        dao.eliminarTodos()
        dao.insertarTodos(combinados)
        return Resultado.Exito(combinados.size)
    }

    suspend fun eliminar(id: Int) = dao.eliminarPorId(id)
}