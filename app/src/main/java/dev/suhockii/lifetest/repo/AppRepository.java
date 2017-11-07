package dev.suhockii.lifetest.repo;

import java.util.List;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import io.reactivex.Completable;
import io.reactivex.Single;

public abstract class AppRepository {

    private final String NOT_IMPLEMENTED = "Not implemented, yet";

    public abstract Single<List<Product>> getProducts();
    public abstract Single<ProductDetails> getDetailsFor(Product product);

    public void saveProducts(List<Product> products) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }

    public void saveProductDetails(ProductDetails product) {
        throw new UnsupportedOperationException(NOT_IMPLEMENTED);
    }
}
