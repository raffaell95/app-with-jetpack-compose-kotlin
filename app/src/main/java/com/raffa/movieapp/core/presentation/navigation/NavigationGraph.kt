package com.raffa.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.raffa.movieapp.core.util.Constants
import com.raffa.movieapp.movie_detail_feature.presentation.MovieDetailSreen
import com.raffa.movieapp.movie_detail_feature.presentation.MovieDetailViewModel
import com.raffa.movieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import com.raffa.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import com.raffa.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import com.raffa.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import com.raffa.movieapp.search_movie_feature.presentation.MovieSearchEvent
import com.raffa.movieapp.search_movie_feature.presentation.MovieSearchScreen
import com.raffa.movieapp.search_movie_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottonNavItem.MoviePopular.route
    ){
        composable(BottonNavItem.MoviePopular.route){

            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {
                    navHostController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }

        composable(BottonNavItem.MovieSearch.route){

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {
                    navHostController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }

        composable(BottonNavItem.MovieFavorite.route){
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState.movies
                .collectAsStateWithLifecycle(initialValue = emptyList())

            MovieFavoriteScreen(
                movies = uiState.value,
                navigateToDetailMovie = {
                    navHostController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }

        composable(
            route = DetailScreenNav.DetailScreen.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onAddFavorite = viewModel::onAddFavorite
            MovieDetailSreen(
                uiState = uiState,
                onAddFavorite = onAddFavorite
            )
        }
    }
}