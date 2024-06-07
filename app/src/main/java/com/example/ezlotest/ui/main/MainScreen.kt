package com.example.ezlotest.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezlotest.R
import com.example.ezlotest.ui.details.DETAILS
import com.example.ezlotest.ui.model.DevicePreview
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Purple80
import com.example.ezlotest.viewmodels.MainViewModel

const val MAIN = "main"

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    MainScreenContent(
        modifier = modifier,
        isLoading = uiState.isLoading,
        devices = uiState.devices,
        selectedPkDevice = viewModel.selectedPkDevice,
        onDeleteDevice = viewModel::onDeleteDevice,
        navigateToDetails = { id, editMode -> navController.navigate("$DETAILS/$id/$editMode") },
        onLongPress = viewModel::onDeviceLongPress,
        reset = viewModel::reset
    )
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isAlertVisible: Boolean = false,
    devices: List<DevicePreview> = emptyList(),
    selectedPkDevice: Int? = 0,
    onDeleteDevice: () -> Unit = {},
    navigateToDetails: (Int, Boolean) -> Unit = { id, isEdit -> },
    onLongPress: (Int) -> Unit = { id -> },
    reset: () -> Unit = {}
) {
    var shouldShowDialog by remember { (mutableStateOf(isAlertVisible)) }
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
    ) {
        if (shouldShowDialog) {
            DeleteDeviceDialog(
                { shouldShowDialog = it },
                selectedPkDevice,
                onDeleteDevice
            )
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 24.dp),
                color = Purple80
            )
        } else {
            ListOfDevices(
                modifier = Modifier.padding(bottom = 64.dp),
                devices = devices,
                onDeviceSelect = { navigateToDetails(it, false) },
                onDeviceLongPress = {
                    onLongPress(it)
                    shouldShowDialog = true
                },
                onEditClick = { navigateToDetails(it, true) })
        }
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { reset() }) {
            Text(text = stringResource(id = R.string.reset))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListOfDevices(
    modifier: Modifier = Modifier,
    devices: List<DevicePreview>,
    onDeviceSelect: (Int) -> Unit = {},
    onDeviceLongPress: (Int) -> Unit = {},
    onEditClick: (Int) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier.then(modifier),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items = devices) {
            DeviceItem(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { onDeviceSelect(it.pkDevice) },
                        onLongClick = { onDeviceLongPress(it.pkDevice) },
                    ), device = it, onEditClick = onEditClick
            )
            HorizontalDivider(thickness = 3.dp, color = Color.LightGray)
        }
    }
}

@Preview
@Composable
fun MainScreenContentWithAlertPreview() {
    EzloTestTheme {
        Surface {
            MainScreenContent(isAlertVisible = true)
        }
    }
}

@Preview
@Composable
fun MainScreenContentWithDevicesPreview() {
    EzloTestTheme {
        Surface {
            MainScreenContent(
                devices = listOf(
                    DevicePreview(0, "Device 1", R.drawable.vera_edge_big),
                    DevicePreview(0, "Device 1", R.drawable.vera_edge_big),
                    DevicePreview(0, "Device 1", R.drawable.vera_edge_big),
                    DevicePreview(0, "Device 1", R.drawable.vera_edge_big),
                )
            )
        }
    }
}