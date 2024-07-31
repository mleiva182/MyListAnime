package com.mleiva.mylistanime.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mleiva.mylistanime.ui.theme.MyListAnimeTheme

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens
 * Creted by: Marcelo Leiva on 18-04-2024 at 20:05
 ***/
@Composable
fun Screen(content: @Composable () -> Unit) {
    MyListAnimeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
} 