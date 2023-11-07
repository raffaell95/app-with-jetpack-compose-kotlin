package com.raffa.movieapp.movie_favorite_feature.di

import com.raffa.movieapp.core.data.local.dao.MovieDao
import com.raffa.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import com.raffa.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import com.raffa.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.raffa.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.AddMovieFavoriteUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.AddMovieFavoriteUseCaseImpl
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.DeleteMovieFavoriteUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.DeleteMovieFavoriteUseCaseImpl
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.GetMoviesFavoriteUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.GetMoviesFavoriteUseCaseImpl
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.IsMovieFavoriteUseCase
import com.raffa.movieapp.movie_favorite_feature.domain.useCase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource{
        return MovieFavoriteLocalDataSourceImpl(dao = dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(localDataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository{
        return MovieFavoriteRepositoryImpl(localDataSource = localDataSource)
    }

    @Provides
    @Singleton
    fun provideMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): GetMoviesFavoriteUseCase{
        return GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideAddMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): AddMovieFavoriteUseCase{
        return AddMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteMoviesFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase{
        return DeleteMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(movieFavoriteRepository: MovieFavoriteRepository): IsMovieFavoriteUseCase{
        return IsMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }
}