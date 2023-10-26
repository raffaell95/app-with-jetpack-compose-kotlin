package com.raffa.movieapp.core.data.remote.response

import com.google.gson.annotations.SerializedName
import com.raffa.movieapp.core.data.remote.model.MovieResult

data class MovieResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieResult>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int
)