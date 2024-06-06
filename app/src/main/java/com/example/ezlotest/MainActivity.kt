package com.example.ezlotest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.ezlotest.di.AppComponent
import com.example.ezlotest.di.AppModule
import com.example.ezlotest.di.DaggerAppComponent
import com.example.ezlotest.navigation.AppNavHost
import com.example.ezlotest.ui.theme.EzloTestTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var appComponent: AppComponent
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
        appComponent.inject(this)
        setContent {
            EzloTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(it)) {
                        AppNavHost(navController = rememberNavController(), viewModelFactory)
                    }
                }
            }
        }
    }
}