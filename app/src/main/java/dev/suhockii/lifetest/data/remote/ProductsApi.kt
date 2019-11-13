package dev.suhockii.lifetest.data.remote

import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse
import dev.suhockii.lifetest.data.remote.entity.ProductListResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    /**
     * The list of products can be obtained from the following method.
     */
    @get:GET("cart/list")
    val products: Observable<ProductListResponse>

    /**
     * A product can be obtained from the following method.
     *
     * @param productId id of the concrete entity.
     */
    @GET("cart/{id}/detail")
    fun getProductDetails(@Path("id") productId: String): Single<ProductDetailsResponse>

    companion object {
        const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/developer-application-test/"
    }
}