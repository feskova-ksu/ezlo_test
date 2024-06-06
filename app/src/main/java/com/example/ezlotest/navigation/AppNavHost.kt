package com.example.ezlotest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ezlotest.ui.DETAILS
import com.example.ezlotest.ui.DEVICE_ID
import com.example.ezlotest.ui.DetailsScreen
import com.example.ezlotest.ui.EDIT_MODE
import com.example.ezlotest.ui.MAIN
import com.example.ezlotest.ui.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModelFactory: ViewModelProvider.Factory,
    startDestination: String = MAIN
) {

    val getVmFactory: () -> ViewModelProvider.Factory = remember { { viewModelFactory } }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN) {
            MainScreen(
                navController = navController,
                viewModel = viewModel(factory = getVmFactory())
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
            DetailsScreen(
                navController = navController, viewModel = viewModel(factory = getVmFactory()),
                deviceId = it.arguments?.getInt(DEVICE_ID) ?: -1,
                editModeOn = it.arguments?.getBoolean(EDIT_MODE) ?: false,
            )
        }
    }
}
