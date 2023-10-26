package com.raffa.movieapp.movie_popular_feature.di

import com.raffa.movieapp.core.data.remote.MovieService
import com.raffa.movieapp.movie_popular_feature.data.repository.MoviePopularRespositoryImpl
import com.raffa.movieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import com.raffa.movieapp.movie_popular_feature.domain.repository.MoviePopularRespository
import com.raffa.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import com.raffa.movieapp.movie_popular_feature.domain.useCase.GetPopularMoviesUseCase
import com.raffa.movieapp.movie_popular_feature.domain.useCase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviePopularFeatureModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource {
        return MoviePopularRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MoviePopularRemoteDataSource): MoviePopularRespository{
        return MoviePopularRespositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRespository: MoviePopularRespository): GetPopularMoviesUseCase{
        return GetPopularMoviesUseCaseImpl(repository = moviePopularRespository)
    }

}