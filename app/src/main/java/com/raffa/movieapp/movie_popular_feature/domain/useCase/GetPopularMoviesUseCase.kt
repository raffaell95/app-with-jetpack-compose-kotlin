package com.raffa.movieapp.movie_popular_feature.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_popular_feature.domain.repository.MoviePopularRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetPopularMoviesUseCase{
    operator fun invoke(params: Params): Flow<PagingData<Movie>>
    data class Params(val pagingConfig: PagingConfig)
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRespository
): GetPopularMoviesUseCase{
    override fun invoke(params: GetPopularMoviesUseCase.Params): Flow<PagingData<Movie>> {
        return try {
            val pagingSource = repository.getPopularMovies()
            Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        }catch (e: Exception){
            emptyFlow()
        }
    }


}