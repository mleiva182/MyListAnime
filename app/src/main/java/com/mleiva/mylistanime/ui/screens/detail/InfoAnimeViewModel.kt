package com.mleiva.mylistanime.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.ui.common.Result
import com.mleiva.mylistanime.ui.common.ifSuccess
import com.mleiva.mylistanime.ui.common.stateAsResultIn
import com.mleiva.mylistanime.usecases.ChangeFavoriteUseCase
import com.mleiva.mylistanime.usecases.FindAnimeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 18-04-2024 at 17:12
 ***/
@HiltViewModel
class InfoAnimeViewModel @Inject constructor(
    @Named("animeId") id: Int,
    findAnimeByIdUseCase: FindAnimeByIdUseCase,
    private val changeFavoriteUseCase: ChangeFavoriteUseCase
):
    ViewModel() {

    /*data class UiState(
        val loading: Boolean = false,
        val anime: Anime? = null
    )

    val state: StateFlow<UiState> = findAnimeByIdUseCase(id)
        .map { UiState(anime = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    fun onFavoriteClicked() {
        state.value.anime?.let {
            viewModelScope.launch {
                changeFavoriteUseCase(it)
            }
        }
    }*/
    val state: StateFlow<Result<Anime>> = findAnimeByIdUseCase(id)
        .stateAsResultIn(scope = viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess {
            viewModelScope.launch {
                changeFavoriteUseCase(it)
            }
        }
    }

}