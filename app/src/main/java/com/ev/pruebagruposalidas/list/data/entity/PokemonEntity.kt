package com.ev.pruebagruposalidas.list.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.ev.pruebagruposalidas.details.data.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
//    val id: Int = 0,
    @PrimaryKey
    val name: String,
    var url: String,
    var height: Int = 0,
    var weight: Int = 0,
    var types: String = ""
)

object Converters {
    @TypeConverter
    fun fromList(value: List<String>): String = value.joinToString(",")

    @TypeConverter
    fun toList(value: String) : List<String> = value.split(",")
}

fun PokemonEntity.toModel(): Pokemon {
    return Pokemon(
        name = this.name,
        height = this.height,
        weight = this.weight,
        types = this.types.split(","),
        id = 0
    )
}