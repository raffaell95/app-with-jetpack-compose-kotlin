package com.raffa.movieapp.core.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raffa.movieapp.ui.theme.black
import com.raffa.movieapp.ui.theme.yellow

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottonNavItem.MoviePopular,
        BottonNavItem.MovieSearch,
        BottonNavItem.MovieFavorite
    )

    BottomNavigation(contentColor = yellow, backgroundColor = black) {
        val navigationBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navigationBackStackEntry?.destination?.route

        items.forEach{ destination ->
            BottomNavigationItem(
                    selected = currentRoute == destination.route, 
                    onClick = {
                        navController.navigate(destination.route){
                            launchSingleTop = true
                        }
                    },
                    icon = { 
                        Icon(imageVector = destination.icon, contentDescription = null)
                    },
                    label = {
                        Text(text = destination.title)
                    }
            )
        }
    }
}

@Preview
@Composable
fun BottonNavigationBarPreview(){
    BottomNavigationBar(navController = rememberNavController())
}