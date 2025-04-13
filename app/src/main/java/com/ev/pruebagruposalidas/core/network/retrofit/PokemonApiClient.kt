package com.ev.pruebagruposalidas.core.network.retrofit

import com.ev.pruebagruposalidas.details.data.response.PokemonResponse
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiClient {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") idPokemon: String): PokemonResponse

    @GET("pokemon?limit=1302")
    suspend fun getAllPokemons(): PokemonListResponse
}