package com.ev.pruebagruposalidas.list.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.network.PokemonRepository
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    private var repository = PokemonRepository()

    private val _list = MutableLiveData<List<PokemonItemList>>(listOf())
    val list: LiveData<List<PokemonItemList>> = _list

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            val response = repository.getPokemonList()
            _list.postValue(response)
        }
    }

}