package com.mleiva.mylistanime.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.ui.common.stateAsResultIn
import com.mleiva.mylistanime.usecases.FetchAnimesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mleiva.mylistanime.ui.common.Result

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 18-04-2024 at 11:13
 ***/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchAnimesUseCase: FetchAnimesUseCase)
    : ViewModel() {

    /*private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            fetchAnimesUseCase().collect{ animes ->
                _state.value = UiState(loading = false, animes = animes)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val animes: List<Anime> = emptyList()
    )*/
    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Anime>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchAnimesUseCase() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }

}