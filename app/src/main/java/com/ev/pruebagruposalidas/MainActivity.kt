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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ev.pruebagruposalidas.data.routes.Routes
import com.ev.pruebagruposalidas.details.ui.PokemonDetailsScreen
import com.ev.pruebagruposalidas.details.ui.PokemonViewModel
import com.ev.pruebagruposalidas.list.ui.ListScree
import com.ev.pruebagruposalidas.list.ui.ListViewModel
import com.ev.pruebagruposalidas.login.ui.LoginScreen
import com.ev.pruebagruposalidas.login.ui.LoginViewModel
import com.ev.pruebagruposalidas.ui.theme.PruebaGrupoSalidasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaGrupoSalidasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Login.route) {
                        composable(route = Routes.Login.route) {
                            val loginViewModel: LoginViewModel by viewModels()
                            LoginScreen(
                                Modifier.padding(innerPadding),
                                loginViewModel,
                                navigationController
                            )
                        }
                        composable(route = Routes.PokemonList.route) { backStackEntry ->
                            val listViewModel: ListViewModel by viewModels()
                            ListScree(
                                Modifier.padding(innerPadding),
                                navigationController,
                                listViewModel,
                                backStackEntry.arguments?.getString(Routes.PokemonList.paramName()).orEmpty()
                            )
                        }

                        composable(route = Routes.PokemonDetails.route,
                            arguments = listOf(navArgument(Routes.PokemonDetails.paramNamePokemon()) { type = NavType.StringType })
                        ) { backStackEntry ->
                            val detailsViewModel: PokemonViewModel = hiltViewModel(backStackEntry)
                            PokemonDetailsScreen(modifier =  Modifier.padding(innerPadding), detailsViewModel)
                        }
                    }
                }
            }
        }
    }
}

