package com.ev.pruebagruposalidas.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ev.pruebagruposalidas.R
import com.ev.pruebagruposalidas.data.routes.Routes

//@Preview(showBackground = true)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    val name by viewModel.name.observeAsState("")
    val isNameValid by viewModel.isNameValid.observeAsState(true)
    val email by viewModel.email.observeAsState("")
    val isEmailValid by viewModel.isEmailValid.observeAsState(true)
    val age by viewModel.age.observeAsState("")
    val isAgeValid by viewModel.isAgeValid.observeAsState(true)

    val isLoginButtonEnabled by viewModel.isButtonLoginEnabled.observeAsState(false)

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier
            .align(Alignment.Center)
            .padding(horizontal = 32.dp)) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Image(
                painter = painterResource(id = R.drawable.trainer_application_form),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Name(name = name, isError = !isNameValid) { viewModel.validateName(it) }
            Email(email = email, isError = !isEmailValid) { viewModel.validateEmail(it) }
            Age(age = age, isError = !isAgeValid) { viewModel.validateAge(it) }
            LoginButton(isLoginButtonEnabled) {
                navController.navigate(Routes.PokemonList.navigateWithName(name))
            }
        }
    }

}

@Composable
fun Name(name: String, isError: Boolean, onTextChange: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Text),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Nombre inválido")
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Email(email: String, isError: Boolean, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Correo") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Correo inválido")
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Age(age: String, isError: Boolean, onTextChange: (String) -> Unit) {
    TextField(
        value = age,
        onValueChange = { onTextChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Edad") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Edad inválida")
            }
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun LoginButton(isEnabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = "Ingresar", modifier = Modifier.padding(vertical = 8.dp))
    }
}