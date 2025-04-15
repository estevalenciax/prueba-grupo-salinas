package com.ev.pruebagruposalidas.list.domain

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.network.PokemonRepository
import javax.inject.Inject

class GetPokemonListByDatabaseUseCase@Inject constructor(private val repository: PokemonRepository) {

    suspend fun getData(): List<PokemonItemList> {
        return repository.getPokemonListFromDatabase()
    }
}