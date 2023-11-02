package com.raffa.movieapp.movie_detail_feature.di

import com.raffa.movieapp.core.data.remote.MovieService
import com.raffa.movieapp.movie_detail_feature.data.repository.MovieDetailsRepositoryImpl
import com.raffa.movieapp.movie_detail_feature.data.source.MovieDetailsRemoteDataSourceImpl
import com.raffa.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.raffa.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.raffa.movieapp.movie_detail_feature.domain.useCase.GetMovieDetailsUseCase
import com.raffa.movieapp.movie_detail_feature.domain.useCase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource{
        return MovieDetailsRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository{
        return MovieDetailsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase{
        return GetMovieDetailsUseCaseImpl(repository = repository)
    }

}