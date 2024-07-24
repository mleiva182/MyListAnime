package com.mleiva.mylistanime.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.mylistanime.data.model.Anime
import com.mleiva.mylistanime.data.repository.AnimesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 18-04-2024 at 17:12
 ***/
class InfoAnimeViewModel(
    private val repository: AnimesRepository,
    private val id: Int):
    ViewModel() {

    /*private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()*/

    data class UiState(
        val loading: Boolean = false,
        val anime: Anime? = null
    )

    val state: StateFlow<UiState> = repository.findInfoAnimeById(id)
        .map { UiState(anime = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    /*init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.findInfoAnimeById(id).collect{ anime ->
                _state.value = UiState(loading = false, anime = anime)
            }
        }
    }*/

    fun onFavoriteClicked() {
        state.value.anime?.let {
            viewModelScope.launch {
                repository.changeFavorite(it)
            }
        }
    }

}