package com.rohit.ecom.presentation.screens.cartScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rohit.ecom.presentation.screens.cartScreen.components.CartItemCard
import com.rohit.ecom.presentation.screens.cartScreen.components.CartSummary
import com.rohit.ecom.presentation.screens.cartScreen.components.EmptyCartView
import com.rohit.ecom.presentation.screens.components.ErrorItem
import com.rohit.ecom.utils.ApiResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    onShopNowClick: () -> Unit,
    viewModel: CartScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Cart", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (uiState is ApiResult.Success) {
                val cartResponse = (uiState as ApiResult.Success).data
                if (cartResponse.content.isNotEmpty()) {
                    val total = cartResponse.content.sumOf { it.totalAmount }
                    CartSummary(
                        totalAmount = total,
                        onCheckoutClick = { /* TODO */ }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is ApiResult.Loading -> {
                    CircularProgressIndicator()
                }

                is ApiResult.Success -> {
                    val cartItems = state.data.content
                    if (cartItems.isEmpty()) {
                        EmptyCartView(onShopNowClick = onShopNowClick)
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
                        ) {
                            items(cartItems, key = { it.id }) { item ->
                                CartItemCard(
                                    cartItem = item,
                                    onRemoveClick = { /* TODO: Implement remove */ }
                                )
                            }
                        }
                    }
                }

                is ApiResult.Error -> {
                    ErrorItem(
                        message = state.message,
                        onRetry = { viewModel.getCartItems() }
                    )
                }
            }
        }
    }
}
