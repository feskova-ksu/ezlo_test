package com.example.ezlotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.ezlotest.di.AppComponent
import com.example.ezlotest.di.AppModule
import com.example.ezlotest.di.DaggerAppComponent
import com.example.ezlotest.ui.MainScreen
import com.example.ezlotest.ui.theme.EzloTestTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
        appComponent.inject(this)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        setContent {
            EzloTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainScreen(modifier = Modifier.padding(it),mainViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EzloTestTheme {
        Greeting("Android")
    }
}