package com.ev.pruebagruposalidas.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ev.pruebagruposalidas.list.data.PokemonItemList

@Composable
fun ListScree(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    listViewModel: ListViewModel, name: String = ""
) {
    val items = listViewModel.list
    val isLoading by listViewModel.isLoading.observeAsState(initial = false)
    val hasMore by listViewModel.hasMore.observeAsState(initial = false)

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "¡Bienvenido $name!", modifier = Modifier.padding(16.dp), fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            List(items, isLoading, hasMore, listViewModel)
        }
    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun List(items: List<PokemonItemList>, isLoading: Boolean, hasMore: Boolean, viewModel: ListViewModel) {
    val listState = rememberLazyListState()

    LaunchedEffect(listState.firstVisibleItemIndex) {
        if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == items.size-1 && !isLoading && hasMore) {
            viewModel.getPokemonList()
        }
    }

    LazyColumn(state = listState) {
        items(items, key = { it.id }) {
            ListItem(it)
        }
        if (isLoading) {
            item {
                LoadingState()
            }
        }

        if (!hasMore) {
            item {
                Text(text = "Hasta aquí llegamos", modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
fun ListItem(model: PokemonItemList) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)) {
            Text(text = model.name)
        }
    }
}