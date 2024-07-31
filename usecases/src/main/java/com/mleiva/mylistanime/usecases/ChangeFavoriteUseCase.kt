package com.mleiva.mylistanime.usecases

import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.data.repository.AnimesRepository
import javax.inject.Inject

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.usecases
 * Creted by: Marcelo Leiva on 29-07-2024 at 18:26
 ***/
class ChangeFavoriteUseCase @Inject constructor(
    private val animesRepository: AnimesRepository
) {
    suspend operator fun invoke(anime: Anime){
        animesRepository.changeFavorite(anime)
    }
}