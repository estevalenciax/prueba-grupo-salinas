package com.ev.pruebagruposalidas.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ListScree(modifier: Modifier = Modifier, navController: NavHostController) {
    Box(modifier = modifier) {
        Text(text = "Hello ListScreen")
    }
}