package com.ev.pruebagruposalidas.details.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.pruebagruposalidas.data.routes.Routes
import com.ev.pruebagruposalidas.details.data.Pokemon
import com.ev.pruebagruposalidas.details.data.network.PokemonDetailsRepository
import kotlinx.coroutines.launch

class PokemonViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val namePokemon: String = savedStateHandle[Routes.PokemonDetails.paramNamePokemon()] ?: ""

    private val repository = PokemonDetailsRepository()

    private val _pokemon = MutableLiveData<Pokemon?>(null)
    val pokemon : LiveData<Pokemon?> = _pokemon

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.value = true
             val response = repository.getPokemonDetails(namePokemon)
            _pokemon.value = response
            _isLoading.value = false
        }
    }

//    companion object {
//        fun provideFactory(idPokemon: Int) : ViewModelProvider.Factory {
//            return  object : ViewModelProvider.Factory {
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    return PokemonViewModel(idPokemon) as T
//                }
//            }
//
//        }
//    }

}