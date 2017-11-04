package dev.suhockii.lifetest.repo;

import java.util.List;

import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import io.reactivex.Single;

public interface ProductRepo {
    /**
     * @return list of {@link Product} from any available source.
     * Note: information from the local database is more important than from remote server.
     */
    Single<List<Product>> getProducts();

    /**
     * Returns details of some product from any available source.
     *
     * @param product which details we would like to retrieve.
     * @return {@link ProductDetailsEntity} object
     */
    Single<ProductDetails> getDetailsFor(Product product);
}
