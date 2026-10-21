package com.upb.examen2.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.upb.examen2.ui.clima.ClimaScreen
import com.upb.examen2.ui.inicio.InicioViewModel
import com.upb.examen2.ui.perros.PerrosScreen

sealed class Pantalla(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector,
) {
    object Clima : Pantalla(
        ruta = "clima",
        titulo = "Clima + Gatos",
        icono = Icons.Default.WbSunny,
    )

    object Perros : Pantalla(
        ruta = "perros",
        titulo = "Perros + Gatos",
        icono = Icons.Default.Pets,
    )
}

val pantallasNavegacion = listOf(
    Pantalla.Clima,
    Pantalla.Perros,
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val inicioViewModel: InicioViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                pantallasNavegacion.forEach { pantalla ->
                    val seleccionada = rutaActual == pantalla.ruta
                    NavigationBarItem(
                        selected = seleccionada,
                        onClick = {
                            if (rutaActual != pantalla.ruta) {
                                navController.navigate(pantalla.ruta) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = pantalla.icono,
                                contentDescription = pantalla.titulo,
                            )
                        },
                        label = {
                            Text(text = pantalla.titulo)
                        },
                    )
                }
            }
        },
    ) { paddingValores ->
        NavHost(
            navController = navController,
            startDestination = Pantalla.Clima.ruta,
            modifier = Modifier.padding(paddingValores),
        ) {
            composable(Pantalla.Clima.ruta) {
                ClimaScreen(viewModel = inicioViewModel)
            }
            composable(Pantalla.Perros.ruta) {
                PerrosScreen(viewModel = inicioViewModel)
            }
        }
    }
}
