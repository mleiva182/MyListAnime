package com.mleiva.mylistanime.data.server

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.server
 * Creted by: Marcelo Leiva on 05-08-2024 at 18:00
 ***/
class MockWebServerRule : TestWatcher() {

    lateinit var server: MockWebServer

    override fun starting(description: Description) {
        server = MockWebServer()
        server.start(8080)
    }

    override fun finished(description: Description) {
        server.shutdown()
    }
}