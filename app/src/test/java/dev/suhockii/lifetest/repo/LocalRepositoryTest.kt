package dev.suhockii.lifetest.repo

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

import java.util.ArrayList

import dev.suhockii.lifetest.data.local.dao.ProductDao
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity
import dev.suhockii.lifetest.data.local.entity.ProductEntity
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.model.ProductDetails
import io.reactivex.Single

import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class LocalRepositoryTest {
    private var productDao: ProductDao? = null
    private var productDetailsDao: ProductDetailsDao? = null
    private var localRepository: AppRepository? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        productDao = Mockito.mock(ProductDao::class.java)
        productDetailsDao = Mockito.mock(ProductDetailsDao::class.java)
        localRepository = LocalRepository(productDao!!, productDetailsDao!!)
    }

    @Test
    @Throws(Exception::class)
    fun getProducts() {
        `when`(productDao!!.products).thenReturn(Single.just(ArrayList()))

        localRepository!!.getProducts()

        verify<ProductDao>(productDao, times(1)).products
    }

    @Test
    @Throws(Exception::class)
    fun getDetailsFor() {
        val productId = "1"
        val productDetails = ProductDetailsEntity(productId)
        val product = ProductEntity(productId)

        `when`(productDetailsDao!!.getProductDetails(productId)).thenReturn(Single.just(productDetails))

        localRepository!!.getDetailsFor(product)

        verify<ProductDetailsDao>(productDetailsDao, times(1)).getProductDetails(productId)
    }

    @Test
    @Throws(Exception::class)
    fun saveProducts() {
        val products = ArrayList<Product>()
        val productEntities = products as List<ProductEntity>

        localRepository!!.saveProducts(products)

        verify<ProductDao>(productDao, times(1)).saveProducts(productEntities)
    }

    @Test
    @Throws(Exception::class)
    fun saveProductDetails() {
        val productId = "1"
        val productDetails = ProductDetailsResponse(productId)
        val productDetailsEntity = ProductDetailsEntity.Builder()
            .id(productDetails.id)
            .build()

        localRepository!!.saveProductDetails(productDetails)

        verify<ProductDetailsDao>(productDetailsDao, times(1)).saveProductDetails(productDetailsEntity)
    }

}