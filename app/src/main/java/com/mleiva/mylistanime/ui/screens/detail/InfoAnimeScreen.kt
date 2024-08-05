package com.mleiva.mylistanime.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mleiva.mylistanime.R
import com.mleiva.mylistanime.ui.common.Result
import com.mleiva.mylistanime.domain.Anime
import com.mleiva.mylistanime.ui.common.AcScaffold
import com.mleiva.mylistanime.ui.common.Screen

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.detail
 * Creted by: Marcelo Leiva on 18-04-2024 at 17:12
 ***/
@Composable
fun InfoAnimeScreen(
    vm: InfoAnimeViewModel = hiltViewModel(),
    onBack: () -> Unit
){
    val state by vm.state.collectAsState()

    InfoAnimeScreen(
        state = state,
        onBack = onBack,
        onFavoriteClicked = vm::onFavoriteClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoAnimeScreen(
    state: Result<Anime>,
    onBack: () -> Unit,
    onFavoriteClicked: () -> Unit
) {

    //val state by vm.state.collectAsState()
    val infoAnimeState = rememberDetailState(state)

    val snackbarHostState = remember { SnackbarHostState() }

    Screen {
        AcScaffold(
            state = state,
            topBar = {
                TopAppBar(
                    title = { Text(text = infoAnimeState.anime?.name ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    }
                )
            },
            floatingActionButton = {

                val favorite = infoAnimeState.anime?.favorite ?: false
                FloatingActionButton(onClick = { onFavoriteClicked() }) {
                    Icon(
                        imageVector = if(favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.favorite)
                    )
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        ) { padding, anime ->
            infoAnimeState.anime?.let { anime ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {

                    AsyncImage(
                        model = anime.images.jpg.imageUrl,
                        contentDescription = anime.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )

                    Card(
                        modifier = Modifier
                            .padding(15.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ){
                        Column(
                            modifier = Modifier.padding(top = 7.dp, start = 20.dp)
                        ) {
                            Row {
                                Text(
                                    text = stringResource(id = R.string.status),
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = anime.status,
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 12.sp
                                )
                            }
                            Row {
                                Text(
                                    text = stringResource(id = R.string.rating),
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = anime.rating,
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 12.sp
                                )
                            }
                            Row {
                                Text(
                                    text = stringResource(id = R.string.studio),
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = anime.studios.get(0).name,
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 12.sp
                                )
                            }
                            Row {
                                Text(
                                    text = stringResource(id = R.string.synopsis),
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = anime.synopsis,
                                    modifier = Modifier.padding(4.dp),
                                    fontSize = 12.sp
                                )
                            }

                        }
                }
                }
            }
        }
    }

}