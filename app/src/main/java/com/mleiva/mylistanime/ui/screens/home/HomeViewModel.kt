package com.mleiva.mylistanime.ui.screens.home

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
 * From: com.mleiva.mylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 18-04-2024 at 11:13
 ***/
class HomeViewModel: ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    private val repository: AnimesRepository = AnimesRepository()

    fun onUiReady() {
        viewModelScope.launch {
            uiState = UiState(loading = true)
            uiState = UiState(loading = false, animes = repository.fetchAnimes())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val animes: List<Anime> = emptyList()
    )

}