package com.example.ezlotest.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezlotest.DetailsViewModel
import com.example.ezlotest.ui.theme.Purple80

const val DETAILS = "details"
const val DEVICE_ID = "deviceId"
const val EDIT_MODE = "editModeOn"

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailsViewModel,
    editModeOn: Boolean
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (uiState.canGoBack) {
            navController.navigateUp()
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp),
                color = Purple80
            )
        } else {
            uiState.device?.let {
                if (editModeOn) {
                    DeviceInfoEdit(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        deviceItem = it,
                        onEdit = { viewModel.saveNewTitle(it) }
                    )
                } else {
                    DeviceInformation(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        deviceItem = it
                    )
                }
            }
        }
    }
}
