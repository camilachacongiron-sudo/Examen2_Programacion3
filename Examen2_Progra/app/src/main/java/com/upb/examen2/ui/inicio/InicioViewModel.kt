package com.upb.examen2.ui.inicio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.upb.examen2.data.ClimaDatoRepository
import com.upb.examen2.data.RazaDatoRepository
import com.upb.examen2.data.local.Examen2Database
import com.upb.examen2.model.ClimaDato
import com.upb.examen2.model.RazaDato
import com.upb.examen2.util.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class InicioUiState(
    val climas: List<ClimaDato> = emptyList(),
    val razas: List<RazaDato> = emptyList(),
    val cargando: Boolean = false,
    val error: String? = null
)

private data class EstadoRed(
    val cargando: Boolean = true,
    val error: String? = null
)

class InicioViewModel(application: Application) : AndroidViewModel(application) {

    private val baseDeDatos = Examen2Database.obtener(application)
    private val razaDatoRepositorio = RazaDatoRepository(baseDeDatos.razaDatoDao())
    private val climaDatoRepositorio = ClimaDatoRepository(baseDeDatos.climaDatoDao())

    private val _estadoRed = MutableStateFlow(EstadoRed())

    // La pantalla siempre lee de Room; las APIs solo sirven para llenarlo.
    val uiState: StateFlow<InicioUiState> = combine(
        climaDatoRepositorio.observarTodos(),
        razaDatoRepositorio.observarTodos(),
        _estadoRed
    ) { climas, razas, red ->
        InicioUiState(
            climas = climas,
            razas = razas,
            cargando = red.cargando,
            error = red.error
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = InicioUiState(cargando = true)
    )

    init {
        sincronizar()
    }

    fun sincronizar() {
        if (_estadoRed.value.cargando && uiState.value.climas.isNotEmpty()) return

        _estadoRed.update { it.copy(cargando = true, error = null) }
        viewModelScope.launch {
            val resultadoRazas = razaDatoRepositorio.sincronizar()
            val resultadoClima = climaDatoRepositorio.sincronizar()

            val error = (resultadoRazas as? Resultado.Error)?.mensaje
                ?: (resultadoClima as? Resultado.Error)?.mensaje

            _estadoRed.update { it.copy(cargando = false, error = error) }
        }
    }
}