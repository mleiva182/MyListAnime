package com.mleiva.myanimelist.testrules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/***
 * Project: MyListAnime
 * From: com.mleiva.myanimelist.testrules
 * Creted by: Marcelo Leiva on 02-08-2024 at 18:54
 ***/
class CoroutinesTestRule: TestWatcher() {

    val testDispatcher = StandardTestDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}