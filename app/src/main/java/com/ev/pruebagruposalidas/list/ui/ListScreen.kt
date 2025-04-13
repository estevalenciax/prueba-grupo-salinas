package com.ev.pruebagruposalidas.list.ui

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ev.pruebagruposalidas.R
import com.ev.pruebagruposalidas.core.routes.Routes
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
    val search by listViewModel.search.observeAsState(initial = "")

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(top = 32.dp))
            Text(text = "¡Bienvenido $name!", modifier = Modifier.padding(16.dp), fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            SearchBar(search) { listViewModel.onSeachChange(it) }
            Spacer(modifier = Modifier.padding(8.dp))
            List(items, isLoading, hasMore, listViewModel) { it ->
                navController.navigate(Routes.PokemonDetails.navigateWithName(it))
            }
        }
    }
}

@Composable
fun SearchBar(search: String, onSearch: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        value = search,
        onValueChange = { onSearch(it) }, placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "search") },
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
        if (gridlistState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == items.size-1 && !isLoading && hasMore) {
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
//        .width(180.dp)
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
//                Text(
//                    text = "#${index+1}",
//                    color = Color.Black,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 10.sp
//                )
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