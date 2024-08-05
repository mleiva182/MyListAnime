package com.mleiva.mylistanime.di

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.di
 * Creted by: Marcelo Leiva on 05-08-2024 at 14:39
 ***/
class HiltTestRunner: AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }

}