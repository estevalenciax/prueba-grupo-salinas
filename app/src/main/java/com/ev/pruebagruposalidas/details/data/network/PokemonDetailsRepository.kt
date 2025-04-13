package com.ev.pruebagruposalidas.details.data.network

import com.ev.pruebagruposalidas.details.data.Pokemon
import com.ev.pruebagruposalidas.details.data.response.PokemonResponse

class PokemonDetailsRepository {
    private val api = PokemonDetailsService()

    suspend fun getPokemonDetails(id: String): Pokemon {
        val response: PokemonResponse = api.getPokemonDetails(id)
        return Pokemon(id = response.id, name = response.name, weight = response.weight, height = response.height, types = response.types.map { it.type.name })
    }
}