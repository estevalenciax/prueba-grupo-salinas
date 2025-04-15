package com.ev.pruebagruposalidas.list.data.network

import com.ev.pruebagruposalidas.data.PokemonDao
import com.ev.pruebagruposalidas.details.data.Pokemon
import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.entity.PokemonEntity
import com.ev.pruebagruposalidas.list.data.response.PokemonListResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api : PokemonService,
    private val database: PokemonDao
) {

    suspend fun getPokemonList(offset: Int, limit: Int = 20): List<PokemonItemList> {
        val response: PokemonListResponse = api.getPokemonList(offset, limit)
        return response.results.map { PokemonItemList(name = it.name, url = it.url) }
    }

    suspend fun getPokemonListFromDatabase(): List<PokemonItemList> {
        val response = database.getPokemonList()
        return response.map { PokemonItemList(name = it.name, url = it.url) }
    }

    suspend fun savePokemonList(pokemonList: List<PokemonItemList>) {
        val pokemonEntities = pokemonList.map { PokemonEntity(name = it.name, url = it.url) }
        database.insertPokemonList(pokemonEntities)
    }
}