package dev.suhockii.lifetest.repo

import dev.suhockii.lifetest.data.remote.ProductsApi
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.model.ProductDetails
import io.reactivex.Single
import javax.inject.Inject

open class RemoteRepository @Inject constructor(private val productsApi: ProductsApi) : AppRepository() {

    override fun getProducts(): Single<List<Product>> {
        return productsApi.products
            .map<List<Product>> { it.products }
            .flatMapIterable { productEntities -> productEntities }
            .map { productEntity -> productEntity }
            .toList()
    }

    override fun getDetailsFor(product: Product): Single<ProductDetails> {
        return productsApi.getProductDetails(product.id)
            .map { this.getFormattedDetails(it) }
    }

    private fun getFormattedDetails(productDetails: ProductDetailsResponse): ProductDetails {
        val description = productDetails.description
        if (description.isEmpty()) {
            productDetails.setDescription("¯\\_(ツ)_/¯")
        }
        return productDetails
    }
}
