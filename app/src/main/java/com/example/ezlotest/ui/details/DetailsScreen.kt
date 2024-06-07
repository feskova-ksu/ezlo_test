package com.example.ezlotest.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezlotest.R
import com.example.ezlotest.ui.model.DeviceDetail
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Purple80
import com.example.ezlotest.viewmodels.DetailsViewModel

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
    DetailsScreenContent(
        modifier = modifier,
        device = uiState.device,
        canGoBack = uiState.canGoBack,
        isLoading = uiState.isLoading,
        editModeOn = editModeOn,
        navigateUp = navController::navigateUp,
        saveNewTitle = viewModel::saveNewTitle
    )
}

@Composable
fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    device: DeviceDetail? = null,
    canGoBack: Boolean = false,
    isLoading: Boolean = false,
    editModeOn: Boolean = false,
    navigateUp: () -> Unit = {},
    saveNewTitle: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
    ) {
        if (canGoBack) {
            navigateUp()
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp),
                color = Purple80
            )
        } else {
            device?.let {
                if (editModeOn) {
                    DeviceInfoEdit(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        deviceItem = it,
                        onEdit = { saveNewTitle(it) }
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

@Preview
@Composable
fun DetailsScreenContentPreview() {
    EzloTestTheme {
        Surface {
            DetailsScreenContent(
                device = DeviceDetail(
                    0,
                    "Device",
                    "12:34:56:67",
                    "Firmware",
                    R.drawable.vera_edge_big,
                    model = "Vera Super"
                )
            )
        }
    }
}

@Preview
@Composable
fun DetailsScreenContentEditOnPreview() {
    EzloTestTheme {
        Surface {
            DetailsScreenContent(
                device = DeviceDetail(
                    0,
                    "Device",
                    "12:34:56:67",
                    "Firmware",
                    R.drawable.vera_edge_big,
                    model = "Vera Super"
                ), editModeOn = true
            )
        }
    }
}