package com.rohit.ecom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.rohit.ecom.presentation.MainViewModel
import com.rohit.ecom.presentation.navigation.NavGraph
import com.rohit.ecom.presentation.navigation.Route
import com.rohit.ecom.ui.theme.EcommerceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {
                val isLoggedIn by viewModel.isLoggedIn.collectAsState()
                
                if (isLoggedIn != null) {
                    val startDestination = if (isLoggedIn == true) {
                        Route.HomeScreen
                    } else {
                        Route.LoginScreen
                    }
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
