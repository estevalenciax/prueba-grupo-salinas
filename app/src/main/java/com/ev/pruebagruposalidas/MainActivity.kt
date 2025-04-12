package com.ev.pruebagruposalidas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ev.pruebagruposalidas.list.ui.ListScree
import com.ev.pruebagruposalidas.list.ui.ListViewModel
import com.ev.pruebagruposalidas.login.ui.LoginScreen
import com.ev.pruebagruposalidas.login.ui.LoginViewModel
import com.ev.pruebagruposalidas.ui.theme.PruebaGrupoSalidasTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaGrupoSalidasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = "login") {
                        composable(route = "login") {
                            val loginViewModel: LoginViewModel by viewModels()
                            LoginScreen(
                                Modifier.padding(innerPadding),
                                loginViewModel,
                                navigationController
                            )
                        }
                        composable(route = "list/{name}") { backStackEntry ->
                            val listViewModel: ListViewModel by viewModels()
                            ListScree(
                                Modifier.padding(innerPadding),
                                navigationController,
                                listViewModel,
                                backStackEntry.arguments?.getString("name").orEmpty()
                            )
                        }
                    }
                }
            }
        }
    }
}

