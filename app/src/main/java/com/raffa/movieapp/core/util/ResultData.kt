package com.raffa.movieapp.core.util

sealed class ResultData<out T>{

    object Loading: ResultData<Nothing>()
    data class Success<out T>(val data: T?): ResultData<T>()
    data class Faiture(val e: Exception?): ResultData<Nothing>()
}
