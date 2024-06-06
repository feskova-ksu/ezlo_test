package com.example.ezlotest.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ezlotest.R
import com.example.ezlotest.ui.theme.Blue20
import com.example.ezlotest.ui.theme.EzloTestTheme
import com.example.ezlotest.ui.theme.Typography

@Composable
fun Header(modifier: Modifier = Modifier){
    Column (modifier = Modifier
        .background(color = Blue20)
        .then(modifier)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(200.dp),
            painter = painterResource(R.drawable.ic_profile),
            contentDescription = "Header image"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Kseniia Feskova", style = Typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
    }
}


@Preview
@Composable
fun HeaderPreview(){
    EzloTestTheme {
        Surface {
            Header(Modifier.padding(8.dp))
        }
    }
}