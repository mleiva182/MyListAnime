package com.mleiva.mylistanime

import com.mleiva.mylistanime.data.repository.AnimesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
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


    @Inject
    lateinit var animesRepository: AnimesRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_it_works() {
        runBlocking {
            val animes = animesRepository.animes.first()
            assertTrue(animes.isEmpty())
        }
    }
}