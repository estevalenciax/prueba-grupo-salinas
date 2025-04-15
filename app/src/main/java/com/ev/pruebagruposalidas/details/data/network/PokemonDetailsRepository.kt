package com.ev.pruebagruposalidas.details.data.network

import com.ev.pruebagruposalidas.data.PokemonDao
import com.ev.pruebagruposalidas.details.data.Pokemon
import com.ev.pruebagruposalidas.details.data.response.PokemonResponse
import com.ev.pruebagruposalidas.details.data.toEntity
import com.ev.pruebagruposalidas.list.data.entity.toModel
import javax.inject.Inject

class PokemonDetailsRepository @Inject constructor(
    private val api : PokemonDetailsService,
    private val database : PokemonDao
) {

    suspend fun getPokemonDetails(id: String): Pokemon {
        val response: PokemonResponse = api.getPokemonDetails(id)
        return Pokemon(id = response.id, name = response.name, weight = response.weight, height = response.height, types = response.types.map { it.type.name })
    }

    suspend fun savePokemonDetails(pokemon: Pokemon) {
        val pokemonSaved = database.getPokemonByName(pokemon.name)
        val pokemonEntity = pokemon.toEntity()
        pokemonEntity.url = pokemonSaved.url
        database.insertPokemon(pokemonEntity)
    }

    suspend fun getPokemonDetailsFromDatabase(name: String): Pokemon {
        val pokemonSaved = database.getPokemonByName(name)
        return pokemonSaved.toModel()
    }
}