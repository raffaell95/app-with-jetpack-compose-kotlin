package com.raffa.movieapp.core.util

import com.raffa.movieapp.BuildConfig

fun String?.toPostUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"