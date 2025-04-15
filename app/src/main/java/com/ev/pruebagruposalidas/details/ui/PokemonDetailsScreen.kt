package com.ev.pruebagruposalidas.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ChipColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ev.pruebagruposalidas.R
import com.ev.pruebagruposalidas.data.state.UiState
import com.ev.pruebagruposalidas.details.data.Pokemon

@Composable
fun PokemonDetailsScreen(modifier: Modifier = Modifier, viewModel: PokemonViewModel) {
    val uiState by viewModel.uiState.observeAsState(UiState.Loading)
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            when (uiState) {
                is UiState.Loading -> LoadingState()
                is UiState.Success -> {
                    val pokemon = (uiState as UiState.Success<Pokemon>).data
                    Content(pokemon = pokemon)
                }
                is UiState.Error -> {
                    val message = (uiState as UiState.Error).message
                    Toast(message = message)
                }
            }
        }
    }
}

@Composable
fun Toast(message: String) {
    val context = LocalContext.current

    LaunchedEffect(message) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
private fun Content(pokemon: Pokemon) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 56.dp), contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.pokeball),
                contentDescription = "pokemon",
                modifier = Modifier.size(250.dp))
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = pokemon.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Height: ${pokemon.height} ft", fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Weight: ${pokemon.weight} lb", fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Types", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.padding(4.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(pokemon.types, key = { it }) {
                    TypeChip(type = it, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))

        }

    }
}

@Composable
fun TypeChip(type: String, color: Color) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(50))
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = type,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}
