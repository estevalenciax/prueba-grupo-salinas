package com.ev.pruebagruposalidas.list.domain

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.network.PokemonRepository
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository : PokemonRepository
) {

    suspend fun searchPokemon(search: String) : List<PokemonItemList> {
        val response = repository.getPokemonList(0, 1302)
        return response.filter { it.name.contains(search) }
    }
}