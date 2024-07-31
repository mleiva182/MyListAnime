package com.mleiva.mylistanime.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.usecases.FetchAnimesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 18-04-2024 at 11:13
 ***/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchAnimesUseCase: FetchAnimesUseCase)
    : ViewModel() {

    private val _state = MutableStateFlow(UiState())
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
    )

}