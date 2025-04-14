package com.ev.pruebagruposalidas.list.domain

import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.network.PokemonRepository
import javax.inject.Inject

class GetPokemonListByPaginationUseCase @Inject constructor(
    private val repository : PokemonRepository
) {

    suspend fun getData(currentPage: Int) : List<PokemonItemList> {
        return repository.getPokemonList(currentPage * 20)
    }
}