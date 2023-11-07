package com.raffa.movieapp.movie_favorite_feature.domain.useCase

import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetMoviesFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>

}
class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
): GetMoviesFavoriteUseCase {
    override suspend fun invoke(): Flow<List<Movie>> {
        return try {
            movieFavoriteRepository.getMovies()
        }catch (e: Exception){
            emptyFlow()
        }
    }


}