package com.aldyaz.univuniv.ui.app

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.aldyaz.univuniv.ui.theme.UnivUnivTheme

@Composable
fun UnivUnivApp() {
    UnivUnivTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "",
            modifier = Modifier.safeDrawingPadding()
        ) {
        }
    }
}
