package com.raffa.movieapp.movie_popular_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raffa.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRespository {

    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}