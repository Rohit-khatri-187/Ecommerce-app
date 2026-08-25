package com.rohit.ecom.presentation.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rohit.ecom.presentation.screens.components.ErrorItem
import com.rohit.ecom.presentation.screens.components.LoadingItem
import com.rohit.ecom.presentation.screens.components.ProductCard

@Composable
fun HomeScreen(
    onProductClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val products = viewModel.products.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                count = products.itemCount,
                key = { index ->
                    products[index]?.id ?: index
                }
            ) { index ->

                products[index]?.let { product ->
                    ProductCard(
                        product = product,
                        onClick = {
                            onProductClick(product.id)
                        }
                    )
                }
            }

            // Loading next page
            if (products.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    LoadingItem()
                }
            }
        }

        // Initial loading
        if (products.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Initial error
        val refreshError = products.loadState.refresh as? LoadState.Error

        if (refreshError != null) {
            ErrorItem(
                message = refreshError.error.message
                    ?: "Something went wrong",
                onRetry = {
                    products.retry()
                }
            )
        }
    }
}