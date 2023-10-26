package com.raffa.movieapp.movie_popular_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.raffa.movieapp.R
import com.raffa.movieapp.core.util.UtilFunctions
import com.raffa.movieapp.movie_popular_feature.presentation.components.MovieContent

import com.raffa.movieapp.movie_popular_feature.presentation.state.MoviePopularState
import com.raffa.movieapp.ui.theme.black
import com.raffa.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = coil.compose.base.R.string.default_popup_window_title),
                        color = white
                    )
                },
                backgroundColor = black
            )

        },
        content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
                onClick = { movieId ->
                    UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                    navigateToDetailMovie(movieId)
                }
            )
        }

    )
}