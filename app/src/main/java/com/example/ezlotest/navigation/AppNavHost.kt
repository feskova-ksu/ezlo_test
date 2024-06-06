package com.example.ezlotest.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ezlotest.DetailsViewModel
import com.example.ezlotest.MainViewModel
import com.example.ezlotest.ui.details.DETAILS
import com.example.ezlotest.ui.details.DEVICE_ID
import com.example.ezlotest.ui.details.DetailsScreen
import com.example.ezlotest.ui.details.EDIT_MODE
import com.example.ezlotest.ui.main.MAIN
import com.example.ezlotest.ui.main.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = MAIN
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN) {
            MainScreen(
                navController = navController,
                viewModel = hiltViewModel<MainViewModel>()
            )
        }
        composable(
            "$DETAILS/{$DEVICE_ID}/{$EDIT_MODE}",
            arguments = listOf(
                navArgument(DEVICE_ID) { type = NavType.IntType; nullable = false },
                navArgument(EDIT_MODE) {
                    type = NavType.BoolType; nullable = false;defaultValue = false
                },
            )
        ) {
            val viewModel =
                hiltViewModel<DetailsViewModel, DetailsViewModel.DetailViewModelFactory> { factory ->
                    factory.create(it.arguments?.getInt(DEVICE_ID) ?: -1)
                }
            DetailsScreen(
                navController = navController, viewModel = viewModel,
                editModeOn = it.arguments?.getBoolean(EDIT_MODE) ?: false,
            )
        }
    }
}
