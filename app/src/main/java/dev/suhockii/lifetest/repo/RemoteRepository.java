package dev.suhockii.lifetest.repo;

import java.util.List;

import javax.inject.Inject;

import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.data.remote.ProductsApi;
import dev.suhockii.lifetest.data.remote.entity.ProductListResponse;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import io.reactivex.Single;

public class RemoteRepository extends AppRepository {
    private ProductsApi productsApi;

    @Inject
    RemoteRepository(ProductsApi productsApi) {
        this.productsApi = productsApi;
    }

    @Override
    public Single<List<Product>> getProducts() {
        return productsApi.getProducts()
                .map(ProductListResponse::getProducts)
                .flatMapIterable(productEntities -> productEntities)
                .map(productEntity -> (Product) productEntity)
                .toList();
    }

    @Override
    public Single<ProductDetails> getDetailsFor(Product product) {
        return productsApi.getProductDetails(product.getId())
                .map(productDetailsResponse -> (ProductDetails) productDetailsResponse);
    }

}
