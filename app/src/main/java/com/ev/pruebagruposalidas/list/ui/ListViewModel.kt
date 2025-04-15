package com.ev.pruebagruposalidas.list.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ev.pruebagruposalidas.data.state.UiState
import com.ev.pruebagruposalidas.list.data.PokemonItemList
import com.ev.pruebagruposalidas.list.domain.GetPokemonListByDatabaseUseCase
import com.ev.pruebagruposalidas.list.domain.GetPokemonListByPaginationUseCase
import com.ev.pruebagruposalidas.list.domain.SearchPokemonByDatabaseUseCase
import com.ev.pruebagruposalidas.list.domain.SearchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPokemonListByPaginationUseCase : GetPokemonListByPaginationUseCase,
    private val searchPokemonUseCase : SearchPokemonUseCase,
    private val getPokemonListByDatabaseUseCase: GetPokemonListByDatabaseUseCase,
    private val searchPokemonByDatabaseUseCase: SearchPokemonByDatabaseUseCase
): ViewModel() {

    private val _list = mutableStateListOf<PokemonItemList>()
    val list: List<PokemonItemList> = _list

    private var currentPage = 0

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasMore = MutableLiveData(true)
    val hasMore: LiveData<Boolean> = _hasMore

    private val _search = MutableLiveData("")
    val search: LiveData<String> = _search

    private val _uiState = MutableLiveData<UiState<List<PokemonItemList>>>()
    val uiState : LiveData<UiState<List<PokemonItemList>>> = _uiState

    init {
        getPokemonList()
    }

    fun getPokemonList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                delay(1000)
                val response = getPokemonListByPaginationUseCase.getData(currentPage)
                if (response.isEmpty()) {
                    _hasMore.value = false
                } else {
                    _list.addAll(response)
                    currentPage++
                    _hasMore.value = true
                }
                _uiState.value = UiState.Success(_list)

            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar datos online: ${e.message}")
                getDataOffline()
            }
        }
    }

    private fun getDataOffline() {
        viewModelScope.launch {
            try {
                val response = getPokemonListByDatabaseUseCase.getData()
                if (response.isEmpty()) {
                    _hasMore.value = false
                } else {
                    _list.clear()
                    _list.addAll(response)
                    _hasMore.value = true
                }
                _uiState.value = UiState.Success(_list)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar datos del almacenamiento interno: ${e.message}")
            }
        }
    }

    fun onRefreshClick() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            currentPage = 0
            try {
                delay(1000)
                val response = getPokemonListByPaginationUseCase.getData(currentPage)
                if (response.isEmpty()) {
                    _hasMore.value = false
                } else {
                    _list.clear()
                    _list.addAll(response)
                    currentPage++
                    _hasMore.value = true
                }
                _uiState.value = UiState.Success(_list)

            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar datos: ${e.message}")
            }
        }
    }

    fun onSeachChange(search: String) {
        _search.value = search
        if (search.isBlank()) {
            onSeachEmpty()
            return
        }
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = searchPokemonUseCase.searchPokemon(search)
                _list.clear()
                _list.addAll(response)
                currentPage++
                _hasMore.value = false
                _uiState.value = UiState.Success(_list)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Buscando en resultados locales...")
                searchPokemonByDatabase(search)
            }
        }
    }

    private fun onSeachEmpty() {
        currentPage = 0
        _hasMore.value = true
        _list.clear()
        getPokemonList()
    }

    private fun searchPokemonByDatabase(search: String) {
        _search.value = search
        if (search.isBlank()) {
            onSeachEmpty()
            return
        }
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val response = searchPokemonByDatabaseUseCase.searchPokemon(search)
                _list.clear()
                _list.addAll(response)
                _hasMore.value = false
                _uiState.value = UiState.Success(_list)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error al cargar la información: ${e.message}")
            }
        }
    }

}