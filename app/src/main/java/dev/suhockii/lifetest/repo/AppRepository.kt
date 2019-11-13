package dev.suhockii.lifetest.repo

import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.model.ProductDetails
import io.reactivex.Single

private const val NOT_IMPLEMENTED = "Not implemented, yet"

abstract class AppRepository {


    abstract fun getProducts(): Single<List<Product>>
    abstract fun getDetailsFor(product: Product): Single<ProductDetails>

    open fun saveProducts(products: List<Product>) {
        throw error(NOT_IMPLEMENTED)
    }

    open fun saveProductDetails(product: ProductDetails) {
        throw error(NOT_IMPLEMENTED)
    }
}
