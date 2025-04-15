package com.ev.pruebagruposalidas.details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.pruebagruposalidas.data.routes.Routes
import com.ev.pruebagruposalidas.data.state.UiState
import com.ev.pruebagruposalidas.details.data.Pokemon
import com.ev.pruebagruposalidas.details.data.network.PokemonDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository : PokemonDetailsRepository
): ViewModel() {
    private val namePokemon: String = savedStateHandle[Routes.PokemonDetails.paramNamePokemon()] ?: ""

    private val _uiState = MutableLiveData<UiState<Pokemon>>(null)
    val uiState : LiveData<UiState<Pokemon>> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = repository.getPokemonDetails(namePokemon)
                repository.savePokemonDetails(response)
                _uiState.value = UiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar la información: ${e.message}")
                getPokemonOffline()
            }
        }
    }

    private fun getPokemonOffline() {
        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetailsFromDatabase(namePokemon)
                _uiState.value = UiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar la información: ${e.message}")
            }
        }
    }

}