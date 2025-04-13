package com.ev.pruebagruposalidas.list.data.network

import com.ev.pruebagruposalidas.core.network.retrofit.PokemonApiClient
import com.ev.pruebagruposalidas.core.network.retrofit.RetrofitClient
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse

class PokemonService() {
    private val retrofit = RetrofitClient.getRetrofit()

    suspend fun getPokemonList(offset: Int, limit: Int = 20) : PokemonListResponse {
        return retrofit.create(PokemonApiClient::class.java).getPokemonList(limit, offset)
    }
}