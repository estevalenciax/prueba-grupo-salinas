package com.ev.pruebagruposalidas.list.data

import java.util.UUID

data class PokemonItemList(val id: String = UUID.randomUUID().toString(), val name: String, val url: String)
