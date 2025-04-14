package com.ev.pruebagruposalidas.details.data.network

import com.ev.pruebagruposalidas.core.network.retrofit.PokemonApiClient
import com.ev.pruebagruposalidas.core.network.retrofit.RetrofitClient
import com.ev.pruebagruposalidas.details.data.response.PokemonResponse
import javax.inject.Inject

class PokemonDetailsService @Inject constructor(
    private val pokemonApiClient: PokemonApiClient
) {

    suspend fun getPokemonDetails(id: String) : PokemonResponse {
        return pokemonApiClient.getPokemonDetails(id)
    }
}