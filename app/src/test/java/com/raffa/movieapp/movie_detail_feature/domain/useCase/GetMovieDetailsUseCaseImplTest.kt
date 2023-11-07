package com.raffa.movieapp.movie_detail_feature.domain.useCase

import androidx.paging.PagingConfig
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raffa.movieapp.core.domain.model.MovieDetailsFactory
import com.raffa.movieapp.core.domain.model.MovieFactory
import com.raffa.movieapp.core.domain.model.MovieSearchFactory
import com.raffa.movieapp.core.domain.model.PagingSourceMoviesFactory
import com.raffa.movieapp.core.util.ResultData
import com.raffa.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.raffa.movieapp.movie_popular_feature.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseImplTest{

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailRepository: MovieDetailsRepository

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailsFactory = MovieDetailsFactory()
        .create(poster = MovieDetailsFactory.Poster.Avengers)

    private val  pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movieFactory)
    )

    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCaseImpl(movieDetailRepository)
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() = runTest {

        //Given
        whenever(movieDetailRepository.getMovieDetails(movieId = movieFactory.id))
            .thenReturn(movieDetailsFactory)

        whenever(movieDetailRepository.getMoviesSimilar(movieId = movieFactory.id))
            .thenReturn(pagingSourceFake)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(movieDetailRepository).getMovieDetails(movieFactory.id)
        verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)

        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result is ResultData.Success).isTrue()
    }

    @Test
    fun `should return Error from ResultStstus when get movieDetails request returns error`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
            .thenThrow(exception)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        verify(movieDetailRepository).getMovieDetails(movieFactory.id)
        Truth.assertThat(result is ResultData.Faiture).isTrue()
    }

    @Test
    fun `should return a ResultStatus error getting similar movies returns an error`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMovieDetails(movieFactory.id))
            .thenThrow(exception)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)
        Truth.assertThat(result is ResultData.Faiture).isTrue()
    }
}