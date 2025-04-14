package com.ev.pruebagruposalidas.list.data.network

import com.ev.pruebagruposalidas.core.network.retrofit.PokemonApiClient
import com.ev.pruebagruposalidas.core.network.retrofit.RetrofitClient
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse
import retrofit2.Retrofit
import javax.inject.Inject

class PokemonService @Inject constructor(
    private val pokemonApiClient: PokemonApiClient
) {

    suspend fun getPokemonList(offset: Int, limit: Int = 20) : PokemonListResponse {
        return pokemonApiClient.getPokemonList(limit, offset)
    }
}