package com.raffa.movieapp.movie_favorite_feature.domain.useCase

import com.google.common.truth.Truth
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
class AddMovieFavoriteUseCaseImplTest{

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val addMovieFavoriteUseCase by lazy {
        AddMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `should return Success from ResultStatus when the repository returns success equal to unit`() = runTest {
        //Given
        whenever(movieFavoriteRepository.insert(movie)).thenReturn(Unit)

        //When
        val result = addMovieFavoriteUseCase.invoke(
            params = AddMovieFavoriteUseCase.Params(
                movie = movie
            )
        ).first()

        //Then
        Truth.assertThat(result).isEqualTo(ResultData.Success(Unit))
    }

    @Test
    fun `must return Failure from ResultStatus when the repository throws an exception`() = runTest {

        //Given
        val exception = RuntimeException()

        whenever(movieFavoriteRepository.insert(movie))
            .thenThrow(exception)

        //When
        val result = addMovieFavoriteUseCase.invoke(
            params = AddMovieFavoriteUseCase.Params(movie = movie)
        ).first()

        Truth.assertThat(result).isEqualTo(ResultData.Faiture(exception))
    }

}