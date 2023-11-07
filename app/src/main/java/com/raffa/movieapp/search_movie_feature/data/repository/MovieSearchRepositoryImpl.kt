package com.raffa.movieapp.search_movie_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.raffa.movieapp.core.domain.model.MovieSearch
import com.raffa.movieapp.core.paging.MovieSearchPagingSource
import com.raffa.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import com.raffa.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
): MovieSearchRepository {
    override fun getSearchMovies(
        query: String
    ): PagingSource<Int, MovieSearch> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}