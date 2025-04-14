package com.ev.pruebagruposalidas.list.data.network

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api : PokemonService
) {

    suspend fun getPokemonList(offset: Int, limit: Int = 20): List<PokemonItemList> {
        val response: PokemonListResponse = api.getPokemonList(offset, limit)
        return response.results.map { PokemonItemList(name = it.name, url = it.url) }
    }
}