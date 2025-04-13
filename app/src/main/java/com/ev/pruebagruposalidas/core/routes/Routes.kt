package com.ev.pruebagruposalidas.core.routes

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object PokemonList: Routes("list/{name}") {
        fun navigateWithName(name: String) = "list/$name"
        fun paramName() = "name"
    }
    object PokemonDetails: Routes("details/{namePokemon}") {
        fun navigateWithName(namePokemon: String) = "details/$namePokemon"
        fun paramNamePokemon() = "namePokemon"
    }
}