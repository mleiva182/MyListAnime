package com.mleiva.mylistanime

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
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime
 * Creted by: Marcelo Leiva on 05-08-2024 at 16:29
 ***/
@HiltAndroidTest
class ExampleInstrumentedTestRoom {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var animesDao: AnimesDao

    @Before
    fun setUp() {
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