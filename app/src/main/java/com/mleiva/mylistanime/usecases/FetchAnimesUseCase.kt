package com.mleiva.mylistanime.usecases

import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.data.repository.AnimesRepository
import kotlinx.coroutines.flow.Flow

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.usecases
 * Creted by: Marcelo Leiva on 29-07-2024 at 18:20
 ***/
class FetchAnimesUseCase(private val animesRepository: AnimesRepository) {
    operator fun invoke(): Flow<List<Anime>> = animesRepository.animes
}