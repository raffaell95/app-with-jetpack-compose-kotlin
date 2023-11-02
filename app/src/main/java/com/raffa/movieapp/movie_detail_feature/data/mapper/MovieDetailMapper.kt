package com.raffa.movieapp.movie_detail_feature.data.mapper

import com.raffa.movieapp.core.domain.model.Movie
import com.raffa.movieapp.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie{
    return Movie(
        id = id,
        title = title,
        imageUrl = backgroundPathUrl.toString(),
        voteAverage = voteAverage
    )
}