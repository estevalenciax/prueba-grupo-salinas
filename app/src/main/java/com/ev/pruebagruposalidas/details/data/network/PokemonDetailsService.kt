package com.ev.pruebagruposalidas.details.data.network

import com.ev.pruebagruposalidas.core.network.retrofit.PokemonApiClient
import com.ev.pruebagruposalidas.core.network.retrofit.RetrofitClient
import com.ev.pruebagruposalidas.details.data.response.PokemonResponse

class PokemonDetailsService() {
    private val retrofit = RetrofitClient.getRetrofit()

    suspend fun getPokemonDetails(id: String) : PokemonResponse {
        return retrofit.create(PokemonApiClient::class.java).getPokemonDetails(id)
//        return retrofit.create(PokemonApiClient::class.java).getPokemonDetailsMock()
    }
}