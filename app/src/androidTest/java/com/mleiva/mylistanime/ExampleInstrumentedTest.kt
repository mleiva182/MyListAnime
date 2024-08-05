package com.mleiva.mylistanime

import com.mleiva.mylistanime.data.datasource.AnimesRemoteDataSource
import com.mleiva.mylistanime.data.repository.AnimesRepository
import com.mleiva.mylistanime.data.server.MockWebServerRule
import com.mleiva.mylistanime.data.server.fromJson
import com.mleiva.mylistanime.domain.Broadcast
import com.mleiva.mylistanime.domain.Genre
import com.mleiva.mylistanime.domain.Images
import com.mleiva.mylistanime.domain.Jpg
import com.mleiva.mylistanime.domain.Studio
import com.mleiva.mylistanime.framework.database.AnimesDao
import com.mleiva.mylistanime.framework.database.DbAnime
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 05-08-2024 at 14:43
 ***/
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @Inject
    lateinit var animesDao: AnimesDao

    @Inject
    lateinit var remoteDataSource: AnimesRemoteDataSource

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("list_animes.json")
        )

        hiltRule.inject()
    }

    @Test
    fun check_4_items_db() = runTest {
        animesDao.insertAnime(buildDatabaseAnimes(1, 2, 3, 4))
        val animes = animesDao.getAllAnimes().first()
        assertEquals(4, animes.size)
    }

    @Test
    fun check_6_items_db() = runTest {
        animesDao.insertAnime(buildDatabaseAnimes(1, 2, 3, 4, 5, 6))
        assertEquals(6, animesDao.getAllAnimes().first().size)
    }

    @Test
    fun check_mock_server_is_working() = runTest {
        val animes = remoteDataSource.fetchAnimes()

        assertEquals("Cowboy Bebop", animes[0].name)
    }

    fun buildDatabaseAnimes(vararg id: Int) = id.map {
        DbAnime(
            id = it,
            images = Images(Jpg("")),
            name = "Anime $it",
            episodes = 0,
            status = "" ,
            rating = "",
            synopsis = "Synopsis $it",
            broadcast = Broadcast("", "", "", ""),
            genres = listOf(Genre(0, "", "", "")),
            studios = listOf(Studio(0, "", "", "")),
            favorite = false
        )
    }
}