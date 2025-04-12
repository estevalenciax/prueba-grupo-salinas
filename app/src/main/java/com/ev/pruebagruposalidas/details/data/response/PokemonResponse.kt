package com.ev.pruebagruposalidas.details.data.response

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,

    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val held_items: List<Any?>,
    val is_default: Boolean,
    val location_area_encounters: String,
    val order: Int,
    val past_types: List<Any?>,
    val species: Species,
    val types: List<Type>
)