package com.mleiva.mylistanime.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

@Serializable
data class Broadcast(
    @SerialName("day")
    val day: String = "",
    @SerialName("time")
    val time: String = "",
    @SerialName("timezone")
    val timeZone: String = "",
    @SerialName("string")
    val string: String = ""
)

@Serializable
data class Genre(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Studio(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Images(
    @SerialName("jpg")
    val jpg: Jpg
)

@Serializable
data class Jpg(
    @SerialName("image_url")
    val imageUrl: String = "",
    @SerialName("large_image_url")
    val largeImageUrl: String = "",
    @SerialName("small_image_url")
    val smallImageUrl: String = ""
)