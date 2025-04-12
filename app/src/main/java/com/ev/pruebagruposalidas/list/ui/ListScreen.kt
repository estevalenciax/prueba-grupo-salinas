package com.ev.pruebagruposalidas.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ev.pruebagruposalidas.list.data.PokemonItemList

@Composable
fun ListScree(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    listViewModel: ListViewModel
) {
    val items by listViewModel.list.observeAsState(listOf())
    Box(modifier = modifier.fillMaxSize()) {
        List(items)
    }
}

@Composable
fun List(items: List<PokemonItemList>) {

    LazyColumn {
        items(items, key = { it.name }) {
            ListItem(it)
        }
    }
}

@Composable
fun ListItem(model: PokemonItemList) {
    Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = model.name)
        }
    }
}