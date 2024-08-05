package com.mleiva.mylistanime.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

const val LOADING_PROGRESS_INDICATOR_TAG = "loading_progress_indicator"

@Composable
fun LoadingProgressIndicator(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .testTag(LOADING_PROGRESS_INDICATOR_TAG)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}