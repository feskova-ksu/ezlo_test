package com.example.ezlotest.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezlotest.R
import com.example.ezlotest.data.model.DeviceDetail
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Typography

@Composable
fun DeviceInfoEdit(
    modifier: Modifier = Modifier,
    deviceItem: DeviceDetail,
    onEdit: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf(deviceItem.title) }
    Column(modifier = Modifier.then(modifier)) {
        RowWithImageAndEditText(device = deviceItem, text) { text = it }
        Spacer(modifier = Modifier.height(16.dp))
        StaticDeviceInfo(deviceItem = deviceItem)
        Button(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                onEdit(text)
            }
        ) {
            Text(text = if (deviceItem.title == text) "Back" else "Save")
        }
    }
}

@Composable
fun RowWithImageAndEditText(
    device: DeviceDetail,
    startText: String,
    onTextChange: (String) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var textFieldState by remember { mutableStateOf(TextFieldValue(startText)) }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(80.dp),
            painter = painterResource(id = device.imageId),
            contentDescription = "Device image"
        )
        Spacer(modifier = Modifier.width(12.dp))
        TextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        textFieldState = TextFieldValue(
                            text = textFieldState.text,
                            selection = TextRange(textFieldState.text.length)
                        )
                    }
                }, keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            value = textFieldState,
            onValueChange = {
                textFieldState = it
                onTextChange(it.text)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.Gray
            ),
            textStyle = Typography.headlineMedium
        )
    }
}

@Preview
@Composable
fun DeviceInfoEditPreview() {
    EzloTestTheme {
        Surface {
            DeviceInfoEdit(
                deviceItem = DeviceDetail(
                    title = "Home number 100",
                    pkDevice = 636363,
                    macAddress = "hdhdhdhd",
                    firmware = "gfksjhjd",
                    model = "Vera super",
                    imageId = R.drawable.vera_edge_big
                )
            )
        }
    }
}