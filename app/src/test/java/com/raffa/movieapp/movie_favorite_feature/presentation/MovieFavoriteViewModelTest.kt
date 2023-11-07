package com.raffa.movieapp.movie_favorite_feature.presentation

import androidx.paging.PagingData
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.GetMoviesFavoriteUseCase
import com.raffa.movieapp.movie_popular_feature.TestDispatcherRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import com.raffa.movieapp.core.domain.model.MovieFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest{

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMovieFavoriteUseCase)
    }

    private val moviesFavorites = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
    )

    @Test
    fun `must validate the data object values when calling list of favorites`() = runTest{
        //Given
        whenever(getMovieFavoriteUseCase.invoke()).thenReturn(
            flowOf(moviesFavorites)
        )

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(moviesFavorites[0])
    }
}