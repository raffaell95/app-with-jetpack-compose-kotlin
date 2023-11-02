package com.raffa.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.raffa.movieapp.movie_popular_feature.data.mapper.toMovie
import retrofit2.HttpException

class MovieSimilarPagingSource(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieId: Int
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {

            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getMoviesSimilar(page = pageNumber, movieId)
            val movies = response.results

            LoadResult.Page(
                data = movies.toMovie(),
                prevKey = if(pageNumber == 1) null else pageNumber - 1,
                nextKey = if(movies.isEmpty()) null else pageNumber + 1
            )

        }catch (exception: Exception){
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }catch (exception: HttpException){
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    companion object{
        private const val LIMIT = 20
    }
}