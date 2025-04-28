package com.example.mvi_shopping_cart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mvi_shopping_cart.presentation.CartScreen
import com.example.mvi_shopping_cart.presentation.CartViewModel
import com.example.mvi_shopping_cart.ui.theme.MVI_Shopping_CartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cartViewModel = CartViewModel()
            enableEdgeToEdge()
            setContent {
                MVI_Shopping_CartTheme {
                    CartScreen(viewModel = cartViewModel)
                }
            }
        }
    }
}

