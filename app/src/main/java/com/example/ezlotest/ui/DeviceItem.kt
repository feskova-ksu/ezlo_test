package com.example.ezlotest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezlotest.R
import com.example.ezlotest.data.model.DevicePreview
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Typography

@Composable
fun DeviceItem(
    modifier: Modifier = Modifier,
    device: DevicePreview
) {
    Row(
        modifier = Modifier.then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(80.dp),
            painter = painterResource(id = device.imageId),
            contentDescription = "Device image"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = device.title,
                style = Typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.DarkGray
            )
            Text(text = "SN:${device.pkDevice}")
        }
        Column {
            Icon(
                modifier = Modifier.clickable { //onEditClick
                },
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow",
                tint = Color.Gray
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview
@Composable
fun DeviceItemPreview() {
    EzloTestTheme {
        Surface {
            DeviceItem(
                modifier = Modifier.padding(8.dp),
                device = DevicePreview(1233434, "Super machina", R.drawable.ic_launcher_background)
            )
        }
    }
}