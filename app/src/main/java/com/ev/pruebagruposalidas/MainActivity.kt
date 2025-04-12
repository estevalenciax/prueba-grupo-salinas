package com.ev.pruebagruposalidas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ev.pruebagruposalidas.list.ui.ListScree
import com.ev.pruebagruposalidas.login.ui.LoginScreen
import com.ev.pruebagruposalidas.login.ui.LoginViewModel
import com.ev.pruebagruposalidas.ui.theme.PruebaGrupoSalidasTheme

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaGrupoSalidasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = "login") {
                        composable(route = "login") {
                            LoginScreen(
                                Modifier.padding(innerPadding),
                                loginViewModel,
                                navigationController
                            )
                        }
                        composable(route = "list") {
                            ListScree(
                                Modifier.padding(innerPadding),
                                navigationController
                            )
                        }
                    }
                }
            }
        }
    }
}

