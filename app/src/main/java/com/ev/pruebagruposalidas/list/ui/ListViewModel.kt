package com.ev.pruebagruposalidas.list.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.domain.GetPokemonListByPaginationUseCase
import com.ev.pruebagruposalidas.list.domain.SearchPokemonUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    private val getPokemonListByPaginationUseCase = GetPokemonListByPaginationUseCase()
    private val searchPokemonUseCase = SearchPokemonUseCase()

    private val _list = mutableStateListOf<PokemonItemList>()
    val list: List<PokemonItemList> = _list

    private var currentPage = 0

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasMore = MutableLiveData(true)
    val hasMore: LiveData<Boolean> = _hasMore

    private val _search = MutableLiveData("")
    val search: LiveData<String> = _search

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000) //delay para que se pueda apreciar el estado de carga
            val response = getPokemonListByPaginationUseCase.getData(currentPage)
            if (response.isEmpty()) {
                _hasMore.value = false
            } else {
                _list.addAll(response)
                currentPage++
                _hasMore.value = true
            }
            _isLoading.value = false

        }
    }

    fun onSeachChange(search: String) {
        _search.value = search
        if (search.isBlank()) {
            onSeachEmpty()
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            val response = searchPokemonUseCase.searchPokemon(search)
            if (response.isEmpty()) {
                _hasMore.value = false
            } else {
                _list.clear()
                _list.addAll(response)
                currentPage++
            }
            _isLoading.value = false
        }

    }

    private fun onSeachEmpty() {
        currentPage = 0
        _hasMore.value = true
        _list.clear()
        getPokemonList()
    }

}