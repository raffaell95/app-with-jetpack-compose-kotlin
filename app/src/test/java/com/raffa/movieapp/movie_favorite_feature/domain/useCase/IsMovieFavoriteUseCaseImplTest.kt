package com.raffa.movieapp.movie_favorite_feature.domain.useCase

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.raffa.movieapp.core.domain.model.MovieFactory
import com.raffa.movieapp.core.util.ResultData
import com.raffa.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.raffa.movieapp.movie_popular_feature.TestDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IsMovieFavoriteUseCaseImplTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val isMovieFavoriteUseCase by lazy {
        IsMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns success with the value equal to true`() = runTest {
        //Given
        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenReturn(true)

        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movie.id)
        ).first()

        Truth.assertThat(result).isEqualTo(ResultData.Success(true))
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns success with the value equal to false`() = runTest {
        //Given
        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenReturn(false)

        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movie.id)
        ).first()

        Truth.assertThat(result).isEqualTo(ResultData.Success(false))
    }

    @Test
    fun `must return Failure from ResultStatus when the repository throws an exception`() = runTest {

        //Given
        val exception = RuntimeException()

        whenever(movieFavoriteRepository.isFavorite(movie.id))
            .thenThrow(exception)

        //When
        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movie.id)
        ).first()

        Truth.assertThat(result).isEqualTo(ResultData.Faiture(exception))
    }
}