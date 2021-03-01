package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.di.AppContainer
import com.example.androiddevchallenge.ui.detail.AnimalDetailScreen
import com.example.androiddevchallenge.ui.home.HomeScreen

@Composable
fun NavGraph(appContainer: AppContainer) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController, appContainer)
        }
        composable("animalDetail/{animalId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val animalId = arguments.getString("animalId") ?: ""
            AnimalDetailScreen(animalId, navController, appContainer)
        }
    }
}