package com.raffa.movieapp.movie_detail_feature.data.source

import com.raffa.movieapp.core.data.remote.MovieService
import com.raffa.movieapp.core.domain.model.MovieDetails
import com.raffa.movieapp.core.domain.model.MoviePaging
import com.raffa.movieapp.core.paging.MovieSimilarPagingSource
import com.raffa.movieapp.core.util.toBackdropUrl
import com.raffa.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.raffa.movieapp.movie_popular_feature.data.mapper.toMovie
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backgroundPathUrl = response.backdropPath.toBackdropUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging {
        val response = service.getMovieSimilar(page = page, movieId = movieId)
        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovie() }
        )
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(this, movieId = movieId)
    }
}