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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezlotest.R
import com.example.ezlotest.ui.model.DeviceDetail
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Typography

@Composable
fun DeviceInformation(modifier: Modifier = Modifier, deviceItem: DeviceDetail) {
    Column(modifier = Modifier.then(modifier)) {
        RowWithImageAndText(device = deviceItem)
        Spacer(modifier = Modifier.height(16.dp))
        StaticDeviceInfo(deviceItem = deviceItem)
    }
}

@Composable
fun RowWithImageAndText(device: DeviceDetail) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(80.dp),
            painter = painterResource(id = device.imageId),
            contentDescription = "Device image"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = device.title,
            style = Typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.DarkGray
        )
    }
}

@Composable
fun StaticDeviceInfo(deviceItem: DeviceDetail) {
    Column {
        Text(
            text = stringResource(id = R.string.sn_info, "${deviceItem.pkDevice}"),
            style = Typography.titleLarge,
            color = Color.Gray
        )
        Text(
            text = stringResource(id = R.string.mac_address_info, deviceItem.macAddress),
            style = Typography.titleLarge,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.firmware_info, deviceItem.firmware),
            style = Typography.titleLarge,
            color = Color.Gray
        )
        Text(
            text = stringResource(id = R.string.model_info, deviceItem.model),
            style = Typography.titleLarge, color = Color.Gray
        )
    }
}

@Preview
@Composable
fun DeviceInformationPreview() {
    EzloTestTheme {
        Surface {
            DeviceInformation(
                modifier = Modifier.padding(8.dp),
                deviceItem =
                DeviceDetail(
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