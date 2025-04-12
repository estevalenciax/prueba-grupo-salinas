package com.ev.pruebagruposalidas.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ev.pruebagruposalidas.details.data.Pokemon

@Composable
fun PokemonDetailsScreen(modifier: Modifier = Modifier, viewModel: PokemonViewModel) {
    val pokemon by viewModel.pokemon.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            pokemon?.let {
                Data(it)
            }
            if (isLoading == true) {
                LoadingState()
            }
        }
    }
}

@Composable
fun Data(pokemon: Pokemon) {
    Column {
        Text(text = "Nombre")
        Text(text = pokemon.name)
        Text(text = "Altura")
        Text(text = pokemon.height.toString())
        Text(text = "Peso")
        Text(text = pokemon.weight.toString())
    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}