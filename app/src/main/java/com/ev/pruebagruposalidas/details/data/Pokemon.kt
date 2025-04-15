package com.ev.pruebagruposalidas.details.data

import com.ev.pruebagruposalidas.list.data.entity.PokemonEntity

data class Pokemon(val id: Int, val name: String, val height: Int, val weight: Int, val types: List<String>)

fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        name = this.name,
        url = "",
        height = this.height,
        weight = this.weight,
        types = this.types.joinToString(",")
    )
}
