package com.raffa.movieapp.search_movie_feature.data.source

import com.raffa.movieapp.core.data.remote.MovieService
import com.raffa.movieapp.core.data.remote.response.SearchResponse
import com.raffa.movieapp.core.paging.MovieSearchPagingSource
import com.raffa.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): SearchResponse {
        return service.searchMovie(page = page, query = query)
    }
}