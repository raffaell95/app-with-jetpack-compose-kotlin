package com.raffa.movieapp.core.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navHostController)
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)){
                NavigationGraph(navHostController = navHostController)
            }
        }
    )
}