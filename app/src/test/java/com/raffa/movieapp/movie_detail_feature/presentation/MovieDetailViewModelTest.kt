package com.raffa.movieapp.movie_detail_feature.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.raffa.movieapp.movie_detail_feature.domain.useCase.GetMovieDetailsUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.AddMovieFavoriteUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.DeleteMovieFavoriteUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.IsMovieFavoriteUseCase
import com.raffa.movieapp.movie_popular_feature.TestDispatcherRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raffa.movieapp.core.domain.model.MovieDetailsFactory
import com.raffa.movieapp.core.domain.model.MovieFactory
import com.raffa.movieapp.core.util.ResultData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    lateinit var addMovieFavoriteUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailsFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingData = PagingData.from((
            listOf(
                MovieFactory().create(poster = MovieFactory.Poster.Avengers),
                MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
            )
            ))

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            addMovieFavoriteUseCase = addMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase = deleteMovieFavoriteUseCase,
            isMovieFavoriteUseCase = isMovieFavoriteUseCase,
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
            }
        )
    }

    @Test
    fun `must notify uiState with Success when get movies similar and movie details returns success`() = runTest {

        //Given
        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(pagingData) to movieDetailsFactory))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()
        //When
        viewModel.uiState.isLoading

        //Then
        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
        assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)

        val movieDetails = viewModel.uiState.movieDetails
        val results = viewModel.uiState.results
        assertThat(movieDetails).isNotNull()
        assertThat(results).isNotNull()
    }

    @Test
    fun `must notify uiState with Failure when get movie details and returns exception`() = runTest {

        //Given
        val exception = Exception("Um erro ocorreu!")
        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Faiture(exception))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Faiture(exception)))

        //Then
        viewModel.uiState.isLoading

        //Then
        val error = viewModel.uiState.error
        assertThat(exception.message).isEqualTo(error)
    }

    @Test
    fun `must call delete favorite and notify of uiState with filled favorite icon when current icon is checked`() = runTest {

        //Given
        whenever(deleteMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
        val checkdArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.onAddFavorite(movie = movie)

        //Then
        verify(deleteMovieFavoriteUseCase).invoke(deleteArgumentCaptor.capture())
        assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkdArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkdArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }

    @Test
    fun `must notfy uiState with filled favorite icon when current icon is unchecked`() = runTest {

        //Given
        whenever(addMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(false)))

        val addArgumentCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
        val checkdArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.onAddFavorite(movie = movie)

        //Then
        verify(addMovieFavoriteUseCase).invoke(addArgumentCaptor.capture())
        assertThat(movie).isEqualTo(addArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkdArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkdArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState bookmark icon filled in if bookmark check returns true`() = runTest {

        //Given
        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(pagingData) to movieDetailsFactory))

        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.uiState.isLoading

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }

    @Test
    fun `must notify uiState bookmark icon filled in if bookmark check returns false`() = runTest {

        //Given
        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(false)))

        whenever(getMovieDetailsUseCase.invoke(any()))
            .thenReturn(ResultData.Success(flowOf(pagingData) to movieDetailsFactory))

        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.uiState.isLoading

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(Color.White).isEqualTo(iconColor)
    }

}