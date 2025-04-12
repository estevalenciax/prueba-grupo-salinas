package com.ev.pruebagruposalidas.core.network.retrofit

import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse
import retrofit2.http.GET

interface PokemonApiClient {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse
}