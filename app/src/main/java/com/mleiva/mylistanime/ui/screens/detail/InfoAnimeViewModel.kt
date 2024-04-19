package com.mleiva.mylistanime.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.mylistanime.data.model.Anime
import com.mleiva.mylistanime.data.repository.AnimesRepository
import kotlinx.coroutines.launch

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 18-04-2024 at 17:12
 ***/
class InfoAnimeViewModel(private val id: Int): ViewModel() {

    private val repository: AnimesRepository = AnimesRepository()

    var state by mutableStateOf(UiState())
        private set

    data class UiState(
        val loading: Boolean = false,
        val anime: Anime? = null
    )

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, anime = repository.findInfoAnimeById(id))
        }
    }

}