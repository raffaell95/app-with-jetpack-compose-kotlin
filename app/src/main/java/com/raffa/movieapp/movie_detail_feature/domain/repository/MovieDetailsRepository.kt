package com.raffa.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}