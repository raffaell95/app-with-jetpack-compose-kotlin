package com.raffa.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_popular_feature.data.mapper.toMovie
import com.raffa.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import retrofit2.HttpException

class MoviePagingSource(
    private val remoteDataSource: MoviePopularRemoteDataSource
): PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let{ anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val moviePaging = remoteDataSource.getPopularMovies(page = pageNumber)

            val movies = moviePaging.movies
            val totalPaging = moviePaging.totalPages

            LoadResult.Page(
                data = movies,
                prevKey = if(pageNumber == 1) null else pageNumber - 1,
                nextKey = if(pageNumber == totalPaging) null else pageNumber + 1
            )

        }catch (exception: Exception){
            return LoadResult.Error(exception)
        }
    }

    companion object{
        private const val LIMIT = 20
    }
}