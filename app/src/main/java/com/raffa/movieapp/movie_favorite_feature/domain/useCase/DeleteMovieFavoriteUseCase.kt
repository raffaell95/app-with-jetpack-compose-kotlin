package com.raffa.movieapp.movie_favorite_feature.domain.useCase

import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.util.ResultData
import com.raffa.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(val movie: Movie)

}
class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
): DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            try {
                val insert = movieFavoriteRepository.delete(params.movie)
                emit(ResultData.Success(insert))
            }catch (e: Exception){
                emit(ResultData.Faiture(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}