package com.example.ezlotest.ui.main

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ezlotest.R
import com.example.ezlotest.ui.theme.EzloTestTheme

@Composable
fun DeleteDeviceDialog(
    changeShow: (Boolean) -> Unit = {},
    selectedPkDevice: Int? = 0,
    onDeleteDevice: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {
            changeShow(false)
        },
        title = { Text(text = stringResource(id = R.string.delete_title)) },
        text = {
            Text(
                text = stringResource(
                    id = R.string.delete_description,
                    "$selectedPkDevice"
                )
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onDeleteDevice()
                    changeShow(false)
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
                onClick = {
                    changeShow(false)
                }
            ) {
                Text(
                    text = "Cancel",
                    color = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun DeleteDeviceDialogPreview() {
    EzloTestTheme {
        Surface {
            DeleteDeviceDialog()
        }
    }
}