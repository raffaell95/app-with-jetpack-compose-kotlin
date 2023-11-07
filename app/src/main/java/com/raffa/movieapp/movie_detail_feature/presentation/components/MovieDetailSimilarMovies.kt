package com.raffa.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_popular_feature.presentation.components.MovieItem
import com.raffa.movieapp.core.presentation.components.common.ErrorScreen
import com.raffa.movieapp.core.presentation.components.common.LoadingView

@Composable
fun MocieDetailSimilarMovies(
    pagingMoviesSimiler: LazyPagingItems<Movie>,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        items(pagingMoviesSimiler.itemCount){ index ->
            val movie = pagingMoviesSimiler[index]
            movie?.let {
                MovieItem(
                    voteAverage = it.voteAverage,
                    imageUrl =  it.imageUrl,
                    id = it.id,
                    onClick = {

                    }
                )
            }
        }
        pagingMoviesSimiler.apply {
            when{

                loadState.refresh is LoadState.Loading ->{
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }){
                        LoadingView()
                    }
                }

                loadState.append is LoadState.Loading ->{
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }){
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Error ->{

                    val error = pagingMoviesSimiler.loadState.refresh as LoadState.Error

                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }){
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }

                loadState.append is LoadState.Error ->{
                    val error = pagingMoviesSimiler.loadState.refresh as LoadState.Error

                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }){
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }

            }
        }
    }
}