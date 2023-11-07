package com.raffa.movieapp.core.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.raffa.movieapp.ui.theme.black
import com.raffa.movieapp.ui.theme.white

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = white,
    backgroundColor: Color = black
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                color = textColor
            )
        },
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}