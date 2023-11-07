package com.raffa.movieapp.movie_detail_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.raffa.movieapp.R
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.presentation.components.common.MovieAppBar
import com.raffa.movieapp.movie_detail_feature.presentation.components.MovieDetailsContent
import com.raffa.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import com.raffa.movieapp.ui.theme.black
import com.raffa.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailSreen(
    uiState: MovieDetailState,
    onAddFavorite: (Movie) -> Unit
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
                MovieAppBar(title = R.string.detail_movie)
        },
        content = {
            uiState.movieDetails?.let { movie ->
                MovieDetailsContent(
                    movieDetails = movie,
                    pagingMovieSimilar = pagingMoviesSimilar,
                    isLoading = uiState.isLoading,
                    isError = uiState.error,
                    iconColor = uiState.iconColor,
                    onAddFavorite = { movie ->
                        onAddFavorite(movie)
                    }
                )
            }
        }
    )

}