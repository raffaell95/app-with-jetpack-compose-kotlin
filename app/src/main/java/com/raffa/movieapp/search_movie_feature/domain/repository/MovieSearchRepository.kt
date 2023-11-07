package com.raffa.movieapp.search_movie_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.raffa.movieapp.core.domain.model.MovieSearch
import com.raffa.movieapp.core.presentation.navigation.BottonNavItem
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getSearchMovies(query: String): PagingSource<Int, MovieSearch>
}