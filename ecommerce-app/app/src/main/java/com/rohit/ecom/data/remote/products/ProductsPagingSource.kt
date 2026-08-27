package com.rohit.ecom.data.remote.products

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rohit.ecom.data.remote.products.dto.Product

class ProductsPagingSource(
    private val productsApi: ProductsApi
) : PagingSource<Int, Product>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Product> {

        return try {
            val page = params.key ?: 0
            val response = productsApi.getProducts(
                page = page,
                size = params.loadSize
            )

            LoadResult.Page(
                data = response.content,
                prevKey = if (page == 0) {
                    null
                } else {
                    page - 1
                },
                nextKey = if (response.isLast) {
                    null
                } else {
                    page + 1
                }
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Product>
    ): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { page ->
                page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
            }
        }
    }
}