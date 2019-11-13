package dev.suhockii.lifetest.data.remote.entity

import dev.suhockii.lifetest.model.Product

class ProductListResponse {
    private val products: List<ProductResponse>? = null

    fun getProducts(): List<Product>? {
        return products
    }
}
