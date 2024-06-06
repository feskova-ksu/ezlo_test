package com.example.ezlotest.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezlotest.MainViewModel
import com.example.ezlotest.R
import com.example.ezlotest.data.model.DevicePreview
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Purple80

const val MAIN = "main"
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {

    val uiState by viewModel.uiState.collectAsState()
    var shouldShowDialog by remember { (mutableStateOf(false)) }
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
    ) {
        Header(Modifier.padding(8.dp))
        if (shouldShowDialog) {
            AlertDialog(
                onDismissRequest = { shouldShowDialog = false },
                title = { Text(text = "Delete device") },
                text = { Text(text = "Do you really want to delete this device?\nSN:${viewModel.selectedPkDevice}") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.onDeleteDevice()
                            shouldShowDialog = false
                        }
                    ) {
                        Text(
                            text = "Confirm",
                            color = Color.White
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { shouldShowDialog = false }
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color.White
                        )
                    }
                }
            )
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp),
                color = Purple80
            )
        } else {
            ListOfDevices(devices = uiState.devices, onDeviceSelect = {
                navController.navigate("$DETAILS/$it/${false}")
            },
                onDeviceLongPress = {
                    viewModel.onDeviceLongPress(it)
                    shouldShowDialog = true
                }, onEditClick = {
                    navController.navigate("$DETAILS/$it/${true}")
                })
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListOfDevices(
    devices: List<DevicePreview>,
    onDeviceSelect: (Int) -> Unit = {},
    onDeviceLongPress: (Int) -> Unit = {},
    onEditClick: (Int) -> Unit = {},
) {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = devices) {
            DeviceItem(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { onDeviceSelect(it.pkDevice) },
                        onLongClick = { onDeviceLongPress(it.pkDevice) },
                    ), device = it, onEditClick = onEditClick
            )
            Divider(color = Color.LightGray, thickness = 3.dp)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    EzloTestTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Header(Modifier.padding(8.dp))
                ListOfDevices(
                    devices = listOf(
                        DevicePreview(
                            0, "Super machina", R.drawable.vera_edge_big
                        ),
                        DevicePreview(
                            1, "Super machina 2", R.drawable.vera_secure_big
                        )
                    )
                )
            }
        }
    }
}