package com.mleiva.mylistanime.domain

import com.mleiva.mylistanime.framework.remote.Broadcast
import com.mleiva.mylistanime.framework.remote.Genre
import com.mleiva.mylistanime.framework.remote.Images
import com.mleiva.mylistanime.framework.remote.Studio

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.data.model
 * Creted by: Marcelo Leiva on 18-04-2024 at 10:44
 ***/
data class Anime(
    val id: Int,
    val images: Images,
    val name: String,
    val episodes: Int,
    val status: String,
    val rating: String,
    val synopsis: String,
    val broadcast: Broadcast,
    val genres: List<Genre>,
    val studios: List<Studio>,
    val favorite: Boolean
)
