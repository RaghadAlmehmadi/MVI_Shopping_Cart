package com.example.mvi_shopping_cart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.UUID

@Composable
fun CartScreen(viewModel: CartViewModel) {
    val cartState by viewModel.cartState.collectAsState()
    var newItemName by remember { mutableStateOf("") }
    var newQuantity by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = newItemName,
                onValueChange = { newItemName = it },
                label = { Text("Item Name") },
                modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
            )

            TextField(
                value = newQuantity,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        newQuantity = input
                    }
                },
                label = { Text("Qty") },
                modifier = Modifier
                        .width(80.dp)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (newItemName.isNotBlank() && newQuantity.isNotBlank()) {
                    val quantityInt = newQuantity.toIntOrNull() ?: 1
                    val newCartItem = CartItem(
                        id = UUID.randomUUID().toString(),
                        name = newItemName,
                        quantity = quantityInt
                    )
                    viewModel.handleIntent(CartIntent.AddItem(newCartItem))
                    newItemName = ""
                    newQuantity = ""
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Add")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (cartState) {
            is CartState.Loading -> {
                CircularProgressIndicator()
            }
            is CartState.Success -> {
                val items = (cartState as CartState.Success).items
                LazyColumn {
                    items(items) { item ->
                        Text(
                            text = "${item.name} (Qty: ${item.quantity})",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
            is CartState.Error -> {
                Text("Error loading cart", color = Color.Red)
            }
        }
    }
}
