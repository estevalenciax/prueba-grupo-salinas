package com.ev.pruebagruposalidas.list.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ev.pruebagruposalidas.details.data.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    var url: String,
    var height: Int = 0,
    var weight: Int = 0,
    var types: String = ""
)

fun PokemonEntity.toModel(): Pokemon {
    return Pokemon(
        name = this.name,
        height = this.height,
        weight = this.weight,
        types = this.types.split(","),
        id = 0
    )
}