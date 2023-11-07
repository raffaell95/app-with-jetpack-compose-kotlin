package com.raffa.movieapp.search_movie_feature.domain.source

import com.raffa.movieapp.core.domain.model.MovieSearchPaging
import com.raffa.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging
}