package com.raffa.movieapp.movie_popular_feature.domain.useCase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_popular_feature.domain.repository.MoviePopularRespository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularMoviesUseCase{
    operator fun invoke(): Flow<PagingData<Movie>>
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRespository
): GetPopularMoviesUseCase{

    override fun invoke(): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }

}