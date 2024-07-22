package com.mleiva.mylistanime.data.repository

import retrofit2.http.GET
import retrofit2.http.Path

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.repository
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:52
 ***/
interface AnimesService {

    @GET("anime")
    suspend fun fetchAnimes(): RemoteResult

    @GET("anime/{id}")
    suspend fun fetchInfoAnime(@Path("id") id: Int): RemoteResultData


}