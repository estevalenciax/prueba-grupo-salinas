package com.ev.pruebagruposalidas.list.data.network

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse

class PokemonRepository {
    private val api = PokemonService()

    suspend fun getPokemonList(offset: Int): List<PokemonItemList> {
        val response: PokemonListResponse = api.getPokemonList(offset)
        return response.results.map { PokemonItemList(name = it.name, url = it.url) }
    }
}