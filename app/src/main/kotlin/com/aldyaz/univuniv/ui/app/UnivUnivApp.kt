package com.aldyaz.univuniv.ui.app

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aldyaz.univuniv.ui.home.HomePage
import com.aldyaz.univuniv.ui.home.HomeScreen
import com.aldyaz.univuniv.ui.search.SearchPage
import com.aldyaz.univuniv.ui.search.SearchScreen
import com.aldyaz.univuniv.ui.theme.UnivUnivTheme

@Composable
fun UnivUnivApp() {
    UnivUnivTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = HomeScreen,
            modifier = Modifier.safeDrawingPadding()
        ) {
            composable<HomeScreen> {
                HomePage(
                    onClickSearch = {
                        navController.navigate(SearchScreen)
                    },
                    onClickItem = {
                    }
                )
            }

            composable<SearchScreen> {
                SearchPage(
                    onClickItem = {
                    }
                )
            }
        }
    }
}
