package com.jasper.mycakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.jasper.mycakeapp.presentation.CakeViewModel
import com.jasper.mycakeapp.presentation.CakesListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<CakeViewModel>()
            CakesListScreen(viewModel)
        }
    }
}