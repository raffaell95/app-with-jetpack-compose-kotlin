package com.raffa.movieapp.search_movie_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.raffa.movieapp.R
import com.raffa.movieapp.search_movie_feature.presentation.components.SearchContent
import com.raffa.movieapp.search_movie_feature.presentation.state.MovieSearchState
import com.raffa.movieapp.ui.theme.black
import com.raffa.movieapp.ui.theme.white

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {

    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.search_movies),
                        color = white)
                },
                backgroundColor = black
            )
        },
        content = { paddingValues ->
            SearchContent(
                modifier = Modifier,
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                           onFetch(it)
                },
                onEvent = {
                          onEvent(it)
                },
                onDetail = {movieId -> navigateToDetailMovie(movieId) }
            )
        }
    )

}