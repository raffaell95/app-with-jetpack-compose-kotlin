package com.raffa.movieapp.movie_popular_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.paging.MoviePagingSource
import com.raffa.movieapp.movie_popular_feature.domain.repository.MoviePopularRespository
import com.raffa.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRespositoryImpl(
    private val remoteDataSource: MoviePopularRemoteDataSource
): MoviePopularRespository {
    override fun getPopularMovies(): PagingSource<Int, Movie> {
       return MoviePagingSource(remoteDataSource)
    }
}