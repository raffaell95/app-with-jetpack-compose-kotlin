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
import com.raffa.movieapp.movie_detail_feature.presentation.components.MovieDetailsContent
import com.raffa.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import com.raffa.movieapp.ui.theme.black
import com.raffa.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailSreen(
    id: Int?,
    uiState: MovieDetailState,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true){
        if(id != null){
            getMovieDetail(MovieDetailEvent.GetMovieDetail(id))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.detail_movie), color = white)
                },
                backgroundColor = black
            )
        },
        content = {
            uiState.movieDetails?.let { movie ->
                MovieDetailsContent(
                    movieDetails = movie,
                    pagingMovieSimilar = pagingMoviesSimilar,
                    isLoading = uiState.isLoading,
                    isError = uiState.error,
                    iconColor = uiState.iconColor,
                    onAddFavorite = {

                    }
                )
            }
        }
    )

}