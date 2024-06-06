package com.example.ezlotest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ezlotest.MainViewModel

const val DETAILS ="details"
const val DEVICE_ID ="deviceId"
const val EDIT_MODE ="editModeOn"
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel,
    deviceId: Int,
    editModeOn: Boolean
) {
    Column {
        Text(text = "Welcome to $deviceId information")
        Text(text = "Edit mode is on: $editModeOn")
    }
}