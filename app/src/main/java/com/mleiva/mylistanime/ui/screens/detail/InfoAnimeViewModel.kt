package com.mleiva.mylistanime.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.mylistanime.data.model.Anime
import com.mleiva.mylistanime.data.repository.AnimesClient
import com.mleiva.mylistanime.data.repository.AnimesRepository
import com.mleiva.mylistanime.data.repository.Broadcast
import com.mleiva.mylistanime.data.repository.Images
import com.mleiva.mylistanime.data.repository.Jpg
import com.mleiva.mylistanime.ui.screens.home.HomeViewModel.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 18-04-2024 at 17:12
 ***/
class InfoAnimeViewModel(private val id: Int): ViewModel() {

    private val repository: AnimesRepository = AnimesRepository(AnimesClient.instance)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val anime: Anime? = null,
        val message: String? = null,
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, anime = repository.findInfoAnimeById(id))
        }
    }


    fun onFavoriteClicked() {
        _state.update { it.copy(message = "Favorite clicked") }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }
}