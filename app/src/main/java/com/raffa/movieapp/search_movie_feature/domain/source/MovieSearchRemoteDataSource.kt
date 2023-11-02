package com.raffa.movieapp.search_movie_feature.domain.source

import com.raffa.movieapp.core.data.remote.response.SearchResponse
import com.raffa.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): SearchResponse
}