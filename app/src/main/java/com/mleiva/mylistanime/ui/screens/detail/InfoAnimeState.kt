package com.mleiva.mylistanime.ui.screens.detail


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.ui.common.Result
/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 03-08-2024 at 10:23
 ***/

@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    private val state: Result<Anime>,
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackbarHostState: SnackbarHostState
) {
    val anime: Anime?
        get() = (state as? Result.Success)?.data

    val topBarTitle: String
        get() = anime?.name ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    state: Result<Anime>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) = remember(state) { DetailState(state, scrollBehavior, snackbarHostState) }