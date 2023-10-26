package com.raffa.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raffa.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import com.raffa.movieapp.movie_popular_feature.presentation.MoviePopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottonNavItem.MoviePopular.route
    ){
        composable(BottonNavItem.MoviePopular.route){

            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {}
            )
        }

        composable(BottonNavItem.MovieSearch.route){}

        composable(BottonNavItem.MovieFavorite.route){}
    }
}