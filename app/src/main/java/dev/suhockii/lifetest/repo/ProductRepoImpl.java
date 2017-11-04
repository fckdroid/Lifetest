package dev.suhockii.lifetest.repo;

import java.util.List;

import javax.inject.Inject;

import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import io.reactivex.Single;

public class ProductRepoImpl implements ProductRepo {
    private ProductDao productDao;
    private ProductDetailsDao productDetailsDao;

    @Inject
    ProductRepoImpl(ProductDao productDao, ProductDetailsDao productDetailsDao) {
        this.productDao = productDao;
        this.productDetailsDao = productDetailsDao;
    }

    @Override
    public Single<List<Product>> getProducts() {
        return productDao.getProducts()
                .toObservable()
                .flatMapIterable(productEntities -> productEntities)
                .map(productEntity -> (Product) productEntity)
                .toList();
    }

    @Override
    public Single<ProductDetails> getDetailsFor(Product product) {
        return productDetailsDao.getProductDetails(product.getId())
                .cast(ProductDetails.class);
    }

}
