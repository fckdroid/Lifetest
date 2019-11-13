package dev.suhockii.lifetest.repo

import android.content.res.Resources

import javax.inject.Inject

import dev.suhockii.lifetest.data.local.dao.ProductDao
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity
import dev.suhockii.lifetest.data.local.entity.ProductEntity
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.model.ProductDetails
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber

class LocalRepository @Inject constructor(
    private val productDao: ProductDao,
    private val productDetailsDao: ProductDetailsDao
) : AppRepository() {

    override val products: Single<List<Product>>
        get() = productDao.products
            .doOnSuccess { productEntities ->
                if (productEntities.isEmpty()) {
                    throw Resources.NotFoundException()
                }
            }
            .toObservable()
            .flatMapIterable { productEntities -> productEntities }
            .cast(Product::class.java)
            .toList()

    override fun getDetailsFor(product: Product): Single<ProductDetails> {
        return productDetailsDao.getProductDetails(product.id)
            .cast(ProductDetails::class.java)
    }

    override fun saveProducts(products: List<Product>) {
        super.saveProducts(products)
        Observable.fromIterable(products)
            .map { product ->
                ProductEntity.Builder()
                    .id(product.id)
                    .imageUrl(product.imageUrl)
                    .name(product.name)
                    .price(product.price)
                    .build()
            }
            .toList()
            .subscribe({ productDao.saveProducts(it) }, { Timber.e(it) })
    }

    override fun saveProductDetails(productDetails: ProductDetails) {
        productDetailsDao.saveProductDetails(
            ProductDetailsEntity.Builder()
                .description(productDetails.description)
                .id(productDetails.id)
                .imageUrl(productDetails.imageUrl)
                .name(productDetails.name)
                .price(productDetails.price)
                .build()
        )
    }
}
