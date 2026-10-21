package com.upb.examen2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.upb.examen2.ui.inicio.InicioScreen

private const val RUTA_INICIO = "inicio"

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RUTA_INICIO) {
        composable(RUTA_INICIO) {
            InicioScreen()
        }
    }
}