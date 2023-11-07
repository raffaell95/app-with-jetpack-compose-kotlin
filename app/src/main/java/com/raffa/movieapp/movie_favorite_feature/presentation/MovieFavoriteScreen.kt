package com.raffa.movieapp.movie_favorite_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.raffa.movieapp.R
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.presentation.components.common.MovieAppBar
import com.raffa.movieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import com.raffa.movieapp.movie_favorite_feature.presentation.state.MovieFavoriteState

@Composable
fun MovieFavoriteScreen(
    movies: List<Movie>,
    navigateToDetailMovie: (Int) -> Unit
) {

    Scaffold(
        backgroundColor = Color.Black,
        topBar = {
            MovieAppBar(title = R.string.favorite_movies)
        },
        content = {paddingValues ->
            MovieFavoriteContent(
                paddingValues = paddingValues,
                movies = movies,
                onClick = { movieId ->
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )
}

@Preview
@Composable
fun MovieFavoriteScreemPreview(){
    MovieFavoriteScreen(
        movies = emptyList()
    ) {}
}