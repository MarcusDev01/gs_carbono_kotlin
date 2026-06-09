package com.github.marcusdev01.gscarbono.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.marcusdev01.gscarbono.screens.EmissaoDetalheScreen
import com.github.marcusdev01.gscarbono.screens.EmissoesScreen
import com.github.marcusdev01.gscarbono.screens.HomeScreen
import com.github.marcusdev01.gscarbono.screens.SatelitesScreen
import com.github.marcusdev01.gscarbono.screens.SobreScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.Emissoes.route) {
            EmissoesScreen(navController = navController)
        }

        composable(
            route = Screen.EmissaoDetalhe.route,
            arguments = listOf(navArgument("emissaoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val emissaoId = backStackEntry.arguments?.getInt("emissaoId") ?: 0
            EmissaoDetalheScreen(navController = navController, emissaoId = emissaoId)
        }

        composable(Screen.Satelites.route) {
            SatelitesScreen(navController = navController)
        }

        composable(Screen.Sobre.route) {
            SobreScreen(navController = navController)
        }
    }
}
