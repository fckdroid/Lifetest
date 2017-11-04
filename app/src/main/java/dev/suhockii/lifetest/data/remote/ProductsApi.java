package dev.suhockii.lifetest.data.remote;

import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse;
import dev.suhockii.lifetest.data.remote.entity.ProductListResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsApi {
    String BASE_URL = "https://s3-eu-west-1.amazonaws.com/developer-application-test/";

    /**
     * The list of products can be obtained from the following method.
     */
    @GET("cart/list")
    Single<ProductListResponse> getProducts();

    /**
     * A product can be obtained from the following method.
     *
     * @param productId id of the concrete entity.
     */
    @GET("cart/{id}/detail")
    Single<ProductDetailsResponse> getProductDetails(@Path("id") int productId);
}