package com.github.marcusdev01.gscarbono.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Emissoes : Screen("emissoes")
    object EmissaoDetalhe : Screen("emissao_detalhe/{emissaoId}") {
        fun createRoute(emissaoId: Int) = "emissao_detalhe/$emissaoId"
    }
    object Satelites : Screen("satelites")
    object Sobre : Screen("sobre")
}
