package com.mleiva.mylistanime.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeScreen
import com.mleiva.mylistanime.ui.screens.detail.InfoAnimeViewModel
import com.mleiva.mylistanime.ui.screens.home.HomeScreen

/***
 * Project: MyListAnime
 * From: com.mleiva.mylistanime.ui.screens.navigation
 * Creted by: Marcelo Leiva on 17-04-2024 at 18:31
 ***/
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onMovieClick = { anime ->
                navController.navigate("detail/${anime.id}")
            })
        }

        composable(
            route = "detail/{animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val animeId = requireNotNull(backStackEntry.arguments?.getInt("animeId"))
            InfoAnimeScreen(
                viewModel { InfoAnimeViewModel(animeId) },
                onBack = { navController.popBackStack() })
        }

    }
}