package com.ev.pruebagruposalidas.list.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ev.pruebagruposalidas.R
import com.ev.pruebagruposalidas.data.state.UiState
import com.ev.pruebagruposalidas.list.data.PokemonItemList

@Composable
fun ListScree(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    listViewModel: ListViewModel, name: String = ""
) {
    val items = listViewModel.list
    val hasMore by listViewModel.hasMore.observeAsState(initial = false)
    val search by listViewModel.search.observeAsState(initial = "")
    val uiState by listViewModel.uiState.observeAsState(initial = UiState.Loading)

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Text(text = "¡Bienvenido $name!", modifier = Modifier.padding(16.dp), fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(8.dp))

            if (uiState is UiState.Error) {
                val message = (uiState as UiState.Error).message
                Toast(message)
            }

            SearchBar(search) { listViewModel.onSeachChange(it) }
            Spacer(modifier = Modifier.padding(8.dp))
            List(items, (uiState is UiState.Loading), hasMore, listViewModel) { it ->
                navController.navigate("details/$it")
            }


        }
        if (search.isBlank()) {
            MyFab { listViewModel.onRefreshClick() }
        }

    }
}

@Composable
fun MyFab(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(onClick = { onClick() }, modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 32.dp, end = 32.dp)) {
            Icon(Icons.Filled.Refresh, contentDescription = "")
        }
    }

}

@Composable
fun Toast(message: String) {
    val context = LocalContext.current

    LaunchedEffect(message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun SearchBar(search: String, onSearch: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        value = search,
        onValueChange = { onSearch(it) }, placeholder = { Text(text = "Buscar...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "buscar") },
        singleLine = true, shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

    )
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}

@Composable
fun List(items: List<PokemonItemList>, isLoading: Boolean, hasMore: Boolean, viewModel: ListViewModel, onItemClick: (String) -> Unit) {
    val gridlistState = rememberLazyGridState()

    LaunchedEffect(gridlistState.firstVisibleItemIndex) {
        if (gridlistState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == items.size-1 && hasMore) {
            viewModel.getPokemonList()
        }
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2), state = gridlistState) {
        items(items, key = { it.id }) { it ->
            ListItem(it, items.indexOf(it).toString()) { name -> onItemClick(name) }
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
fun ListItem(model: PokemonItemList, index: String, onClick: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable { onClick(model.name) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = model.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    TypeChip(type = "Type", color = Color(0xFF63D471))
                }
                Image(
                    painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "pokemon",
                    modifier = Modifier.size(80.dp)
                )
            }
        }
    }
}

@Composable
fun TypeChip(type: String, color: Color) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(50))
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = type,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 8.sp
        )
    }
}