package com.raffa.movieapp.movie_detail_feature.domain.source

import com.raffa.movieapp.core.domain.model.MovieDetails
import com.raffa.movieapp.core.domain.model.MoviePaging
import com.raffa.movieapp.core.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging
    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}