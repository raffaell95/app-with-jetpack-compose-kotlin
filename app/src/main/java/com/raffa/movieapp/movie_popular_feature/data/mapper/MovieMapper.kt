package com.raffa.movieapp.movie_popular_feature.data.mapper

import com.raffa.movieapp.core.data.remote.model.MovieResult
import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.util.toPostUrl


fun MovieResult.toMovie(): Movie{
    return Movie(
        id = id,
        title = title,
        voteAverage = voteAverage,
        imageUrl = posterPath?.toPostUrl() ?: ""
    )
}

fun List<MovieResult>.toMovie() = map{movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult?.posterPath?.toPostUrl() ?: ""
    )
}