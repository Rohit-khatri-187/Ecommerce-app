package com.rohit.ecom.presentation.screens.detailsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailBottomBar
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailDescription
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailHeader
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailInfo
import com.rohit.ecom.utils.ApiResult

@Composable
fun DetailsScreen(
    productId: Int,
    onBackClick: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = productId) {
        viewModel.getProduct(productId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is ApiResult.Loading -> {
                CircularProgressIndicator()
            }

            is ApiResult.Success -> {
                val product = state.data
                Scaffold(
                    bottomBar = {
                        ProductDetailBottomBar(
                            onAddToCart = { /* TODO */ },
                            onBuyNow = { /* TODO */ }
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState())
                    ) {
                        ProductDetailHeader(
                            imageUrl = product.imageUrl,
                            productName = product.name,
                            onBackClick = onBackClick
                        )

                        ProductDetailInfo(
                            name = product.name,
                            category = product.category,
                            stock = product.stock
                        )

                        ProductDetailDescription(
                            description = product.description
                        )
                        
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            is ApiResult.Error -> {
                Text(text = "Error: ${state.message}")
            }
        }
    }
}
