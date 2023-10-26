package com.raffa.movieapp.movie_popular_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_popular_feature.domain.repository.MoviePopularRespository
import com.raffa.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRespositoryImpl(
    private val remoteDataSource: MoviePopularRemoteDataSource
): MoviePopularRespository {
    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}