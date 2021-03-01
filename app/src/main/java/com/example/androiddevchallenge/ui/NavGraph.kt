/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
