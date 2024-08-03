package com.mleiva.mylistanime.ui.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.home
 * Creted by: Marcelo Leiva on 03-08-2024 at 10:28
 ***/
@OptIn(ExperimentalMaterial3Api::class)
class HomeState(
    val scrollBehavior: TopAppBarScrollBehavior
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
) = remember { HomeState(scrollBehavior) }