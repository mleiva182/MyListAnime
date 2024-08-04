package com.mleiva.myanimelist.data

import com.mleiva.myanimelist.sampleAnimes
import com.mleiva.mylistanime.data.datasource.AnimesLocalDataSource
import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.data.repository.AnimesRepository
import com.mleiva.mylistanime.domain.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

/***
 * Project: MyListAnime
 * From: com.mleiva.myanimelist.data
 * Creted by: Marcelo Leiva on 04-08-2024 at 11:20
 ***/
fun buildAnimesRepositoryWith(
    localData: List<Anime> = emptyList(),
    remoteData: List<Anime> = emptyList()
): AnimesRepository {
    val localDataSource = FakeLocalDataSource().apply { inMemoryAnimes.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { animes = remoteData }
    return AnimesRepository(localDataSource, remoteDataSource)
}


class FakeLocalDataSource : AnimesLocalDataSource {

    val inMemoryAnimes = MutableStateFlow<List<Anime>>(emptyList())

    override val animes = inMemoryAnimes

    override fun findAnimeById(id: Int): Flow<Anime?> =
        inMemoryAnimes.map { it.firstOrNull { anime -> anime.id == id } }

    override suspend fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun save(animes: List<Anime>) {
        inMemoryAnimes.value = animes
    }
}

class FakeRemoteDataSource : AnimesRemoteDataSource {

    var animes = sampleAnimes(1, 2, 3, 4)

    override suspend fun fetchAnimes() = animes

    override suspend fun findInfoAnimeById(id: Int): Anime = animes.first { it.id == id }

}
