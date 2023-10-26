package com.raffa.movieapp.movie_popular_feature.domain.source

import com.raffa.movieapp.core.data.remote.response.MovieResponse
import com.raffa.movieapp.core.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    fun getPopularMoviesPagingSource(): MoviePagingSource

    suspend fun getPopularMovies(page: Int): MovieResponse
}