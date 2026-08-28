package com.rohit.ecom.presentation.screens.detailsscreen

import android.widget.Toast
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailBottomBar
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailDescription
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailHeader
import com.rohit.ecom.presentation.screens.detailsscreen.components.ProductDetailInfo
import com.rohit.ecom.presentation.screens.detailsscreen.components.QuantitySelector
import com.rohit.ecom.utils.ApiResult

@Composable
fun DetailsScreen(
    productId: Int,
    onBackClick: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val addToCartState by viewModel.addToCartState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    
    var quantity by rememberSaveable { mutableIntStateOf(1) }

    LaunchedEffect(key1 = productId) {
        viewModel.getProduct(productId)
    }

    LaunchedEffect(key1 = addToCartState) {
        when (val state = addToCartState) {
            is ApiResult.Success -> {
                snackbarHostState.showSnackbar("Product added to cart")
                viewModel.resetAddToCartState()
            }
            is ApiResult.Error -> {
                snackbarHostState.showSnackbar(state.message)
                viewModel.resetAddToCartState()
            }
            else -> {}
        }
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
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        ProductDetailBottomBar(
                            onAddToCart = {
                                viewModel.addToCart(product.id, quantity)
                            },
                            onBuyNow = {
                                // TODO: Handle buy now
                                Toast.makeText(context, "Buy Now clicked", Toast.LENGTH_SHORT).show()
                            },
                            isAddingToCart = addToCartState is ApiResult.Loading
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

                        QuantitySelector(
                            quantity = quantity,
                            onQuantityChange = { quantity = it },
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
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
