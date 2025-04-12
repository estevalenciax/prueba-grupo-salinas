package com.ev.pruebagruposalidas.list.data.response

data class PokemonListResponse(private val count: Int, private val next: String?, private val previous: String?, val results: List<PokemonItemListResponse>)
