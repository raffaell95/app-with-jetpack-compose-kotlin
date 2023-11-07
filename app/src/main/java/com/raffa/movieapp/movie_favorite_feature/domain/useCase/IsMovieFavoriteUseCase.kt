package com.raffa.movieapp.movie_favorite_feature.domain.useCase

import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.util.ResultData
import com.raffa.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(val movieId: Int)

}
class IsMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
): IsMovieFavoriteUseCase {
    override suspend fun invoke(params: IsMovieFavoriteUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            try {
                val isFavorite = movieFavoriteRepository.isFavorite(params.movieId)
                emit(ResultData.Success(isFavorite))
            }catch (e: Exception){
                emit(ResultData.Faiture(e))
            }
        }.flowOn(Dispatchers.IO)
    }

}