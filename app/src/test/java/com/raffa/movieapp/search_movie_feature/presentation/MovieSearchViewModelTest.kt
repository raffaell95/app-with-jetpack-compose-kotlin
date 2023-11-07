package com.raffa.movieapp.search_movie_feature.presentation

 import androidx.paging.PagingData
 import com.google.common.truth.Truth.assertThat
 import com.nhaarman.mockitokotlin2.any
 import com.nhaarman.mockitokotlin2.whenever
 import com.raffa.movieapp.core.domain.model.MovieSearchFactory
 import com.raffa.movieapp.movie_popular_feature.TestDispatcherRule
 import com.raffa.movieapp.search_movie_feature.domain.useCase.GetMovieSearchUseCase
 import kotlinx.coroutines.flow.first
 import kotlinx.coroutines.flow.flowOf
 import kotlinx.coroutines.test.runTest
 import org.junit.Assert.*
 import org.junit.Rule
 import org.junit.Test
 import org.junit.runner.RunWith
 import org.mockito.Mock
 import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieSearchViewModelTest{

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getSearchMoviesUseCase: GetMovieSearchUseCase

    private val viewModel by lazy {
        MovieSearchViewModel(getSearchMoviesUseCase)
    }

    private val fakePagingDataSearchMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when calling movie search paging data`() = runTest {

        //Given
        whenever(getSearchMoviesUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataSearchMovies)
        )

        //When
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns an exception`() = runTest {
        //Given
        whenever(getSearchMoviesUseCase.invoke(any()))
            .thenThrow(RuntimeException())

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNull()
    }

}