package com.raffa.movieapp.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.domain.model.MovieDetails
import com.raffa.movieapp.core.paging.MovieSimilarPagingSource
import com.raffa.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.raffa.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
): MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId = movieId)
    }

    override fun getMoviesSimilar(
        movieId: Int
    ): PagingSource<Int, Movie> {
        return MovieSimilarPagingSource(movieId = movieId,
            remoteDataSource = remoteDataSource)
    }
}