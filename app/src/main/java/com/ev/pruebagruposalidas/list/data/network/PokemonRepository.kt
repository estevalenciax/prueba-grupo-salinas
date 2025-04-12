package com.ev.pruebagruposalidas.list.data.network

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse

class PokemonRepository {
    private val api = PokemonService()

    suspend fun getPokemonList(): List<PokemonItemList> {
        val response: PokemonListResponse = api.getPokemonList()
        return response.results.map { PokemonItemList(name = it.name, url = it.url) }
    }
}