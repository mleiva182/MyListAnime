package com.mleiva.myanimelist

import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.domain.Broadcast
import com.mleiva.mylistanime.domain.Images
import com.mleiva.mylistanime.domain.Jpg

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
    synopsis = "",
    broadcast = Broadcast(),
    genres = emptyList(),
    studios = emptyList(),
    favorite = false
)

fun sampleAnimes(vararg ids: Int) = ids.map { sampleAnime(it) }