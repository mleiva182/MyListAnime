package com.mleiva.mylistanime.framework.remote

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:50
 ***/
object AnimesClient {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/v4/")
        //.client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<AnimesService>()

}