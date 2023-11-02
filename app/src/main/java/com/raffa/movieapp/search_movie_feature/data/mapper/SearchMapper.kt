package com.raffa.movieapp.search_movie_feature.data.mapper

import com.raffa.movieapp.core.data.remote.model.SearchResult
import com.raffa.movieapp.core.domain.model.MovieSearch

fun List<SearchResult>.toMovieSearch() = map{ searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toString(),
        vodeAverage = searchResult.voteAverage
    )
}