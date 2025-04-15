package com.ev.pruebagruposalidas.list.domain

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.network.PokemonRepository
import javax.inject.Inject

class SearchPokemonByDatabaseUseCase @Inject constructor(
    private val repository : PokemonRepository
) {
    suspend fun searchPokemon(search: String) : List<PokemonItemList> {
        return repository.searchPokemonByNameFromDatabase(search)
    }
}