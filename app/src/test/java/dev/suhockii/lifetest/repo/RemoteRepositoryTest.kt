package dev.suhockii.lifetest.repo

import dev.suhockii.lifetest.data.local.entity.ProductEntity
import dev.suhockii.lifetest.data.remote.ProductsApi
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse
import dev.suhockii.lifetest.data.remote.entity.ProductListResponse
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class RemoteRepositoryTest {
    private var productsApi: ProductsApi? = null
    private var remoteRepository: AppRepository? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        productsApi = Mockito.mock(ProductsApi::class.java)
        remoteRepository = RemoteRepository(productsApi!!)
    }

    @Test
    @Throws(Exception::class)
    fun getProducts() {
        `when`(productsApi!!.products).thenReturn(Observable.just(ProductListResponse()))

        remoteRepository!!.getProducts()

        verify<ProductsApi>(productsApi, times(1)).products
    }

    @Test
    @Throws(Exception::class)
    fun getDetailsFor() {
        val productId = "1"
        val product = ProductEntity(productId)
        val detailsResponse = Single.just(ProductDetailsResponse(productId))

        `when`(productsApi!!.getProductDetails(product.id)).thenReturn(detailsResponse)

        remoteRepository!!.getDetailsFor(product)

        verify<ProductsApi>(productsApi, times(1)).getProductDetails(productId)
    }

}