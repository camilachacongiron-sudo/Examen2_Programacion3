package com.upb.examen2.ui.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upb.examen2.model.ClimaDato
import com.upb.examen2.model.RazaDato
import com.upb.examen2.ui.components.MensajeDeError
import com.upb.examen2.util.capitalizar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    viewModel: InicioViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Examen2 Progra") },
                actions = {
                    IconButton(onClick = viewModel::sincronizar) {
                        Icon(Icons.Default.Refresh, contentDescription = "Actualizar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingInterno ->
        InicioContent(
            uiState = uiState,
            onReintentar = viewModel::sincronizar,
            modifier = Modifier.padding(paddingInterno)
        )
    }
}

@Composable
fun InicioContent(
    uiState: InicioUiState,
    onReintentar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sinDatos = uiState.climas.isEmpty() && uiState.razas.isEmpty()
    val error = uiState.error

    when {
        sinDatos && uiState.cargando -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        sinDatos && error != null -> {
            MensajeDeError(
                mensaje = error,
                onReintentar = onReintentar,
                modifier = modifier.fillMaxSize()
            )
        }
        else -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (uiState.cargando) {
                    item { LinearProgressIndicator(modifier = Modifier.fillMaxWidth()) }
                }
                if (error != null) {
                    item {
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                item { Titulo("Clima de La Paz + dato de gato") }
                items(uiState.climas, key = { "clima-${it.id}" }) { ClimaCard(it) }

                item { Titulo("Razas de perros + dato de gato") }
                items(uiState.razas, key = { "raza-${it.id}" }) { RazaCard(it) }
            }
        }
    }
}

@Composable
private fun Titulo(texto: String) {
    Text(
        text = texto,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun ClimaCard(clima: ClimaDato) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "%.1f °C".format(clima.temperatura),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text("Humedad: ${clima.humedad} %", style = MaterialTheme.typography.bodyMedium)
            Text("Viento: %.1f km/h".format(clima.vientoKmh), style = MaterialTheme.typography.bodyMedium)
            Text(
                text = clima.hora,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Dato de gato: ${clima.datoGato}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun RazaCard(raza: RazaDato) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = capitalizar(raza.raza),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            if (raza.subrazas.isNotEmpty()) {
                Text(
                    text = "Subrazas: ${raza.subrazas.joinToString(", ")}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Dato de gato: ${raza.datoGato}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}