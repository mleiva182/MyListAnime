package com.mleiva.mylistanime.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mleiva.mylistanime.App
import com.mleiva.mylistanime.framework.remote.AnimesClient
import com.mleiva.mylistanime.data.repository.AnimesRepository
import com.mleiva.mylistanime.framework.AnimesRoomDataSource
import com.mleiva.mylistanime.framework.AnimesServerDataSource
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeScreen
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeViewModel
import com.mleiva.mylistanime.ui.screens.home.HomeScreen
import com.mleiva.mylistanime.ui.screens.home.HomeViewModel
import com.mleiva.mylistanime.usecases.ChangeFavoriteUseCase
import com.mleiva.mylistanime.usecases.FetchAnimesUseCase
import com.mleiva.mylistanime.usecases.FindAnimeByIdUseCase

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.navigation
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:31
 ***/
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onAnimeClick = { anime ->
                navController.navigate(Screen.Detail.createRoute(anime.id))
            })
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(NavArgs.AnimeId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            InfoAnimeScreen(
                onBack = { navController.popBackStack() })
        }

    }
}

sealed class Screen(val route: String){

    data object Home: Screen("home")
    data object Detail: Screen("detail/{${NavArgs.AnimeId.key}}"){
        fun createRoute(animeId: Int) = "detail/$animeId"
    }

}

enum class NavArgs(val key: String) {
    AnimeId("animeId")
}
