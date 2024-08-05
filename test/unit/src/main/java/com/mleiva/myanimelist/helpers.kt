package com.mleiva.myanimelist

import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.domain.Broadcast
import com.mleiva.mylistanime.domain.Genre
import com.mleiva.mylistanime.domain.Images
import com.mleiva.mylistanime.domain.Jpg
import com.mleiva.mylistanime.domain.Studio

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.usecases
 * Creted by: Marcelo Leiva on 02-08-2024 at 10:37
 ***/
fun sampleAnime(id: Int = 0) = Anime(
    id= id,
    images = Images(Jpg()),
    name= "Anime $id",
    episodes = id,
    status = "",
    rating = "",
    synopsis = "Synopsis $id",
    broadcast = Broadcast(),
    genres = listOf(Genre(id,"","","")),
    studios = listOf(Studio(id,"","","")),
    favorite = false
)

fun sampleAnimes(vararg ids: Int) = ids.map { sampleAnime(it) }