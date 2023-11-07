package com.raffa.movieapp.search_movie_feature.data.mapper

import com.raffa.movieapp.core.data.remote.model.SearchResult
import com.raffa.movieapp.core.domain.model.MovieSearch
import com.raffa.movieapp.core.util.toPostUrl

fun SearchResult.toMovieSearch(): MovieSearch{
    return MovieSearch(
        id = id,
        imageUrl = posterPath.toPostUrl(),
        voteAverage = voteAverage
    )
}

fun List<SearchResult>.toMovieSearch() = map{ searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toString(),
        voteAverage = searchResult.voteAverage
    )
}