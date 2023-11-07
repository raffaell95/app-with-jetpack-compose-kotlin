package com.raffa.movieapp.movie_favorite_feature.domain.useCase

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raffa.movieapp.core.domain.model.MovieFactory
import com.raffa.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.raffa.movieapp.movie_popular_feature.TestDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesFavoriteUseCaseImplTest{

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteResponse: MovieFavoriteRepository

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
    )

    private val getMoviesFavoriteUseCase by lazy {
        GetMoviesFavoriteUseCaseImpl(movieFavoriteResponse)
    }

    @Test
    fun `should return Success from ResultStatus when the repository returns a list of movies`() = runTest {

        //Given
        whenever(movieFavoriteResponse.getMovies()).thenReturn(
            flowOf(movies)
        )

        //When
        val result = getMoviesFavoriteUseCase.invoke().first()

        //Then
        Truth.assertThat(result).isNotEmpty()
        Truth.assertThat(result).contains(movies[1])
    }

    @Test
    fun `should emit an empty stream when exception is thrown when calling the invoke method`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteResponse.getMovies()).thenThrow(exception)

        //When
        val result = getMoviesFavoriteUseCase.invoke().toList()

        //Then
        verify(movieFavoriteResponse).getMovies()
        Truth.assertThat(result).isEmpty()

    }

}