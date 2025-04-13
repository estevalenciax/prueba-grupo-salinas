package com.ev.pruebagruposalidas.list.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.data.network.PokemonRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    private var repository = PokemonRepository()

    private val _list = mutableStateListOf<PokemonItemList>()
    val list: List<PokemonItemList> = _list

    private var currentPage = 0
    private var pageSize = 20

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasMore = MutableLiveData(true)
    val hasMore: LiveData<Boolean> = _hasMore

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000)
            val response = repository.getPokemonList(currentPage * 10)
            if (response.isEmpty()) {
                _hasMore.value = true
            } else {
                _list.addAll(response)
                currentPage++
            }
            _isLoading.value = false

        }
    }

}