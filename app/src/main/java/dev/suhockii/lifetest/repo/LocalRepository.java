package dev.suhockii.lifetest.repo;

import android.content.res.Resources;

import java.util.List;

import javax.inject.Inject;

import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity;
import dev.suhockii.lifetest.data.local.entity.ProductEntity;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

public class LocalRepository extends AppRepository {
    private ProductDao productDao;
    private ProductDetailsDao productDetailsDao;

    @Inject
    LocalRepository(ProductDao productDao, ProductDetailsDao productDetailsDao) {
        this.productDao = productDao;
        this.productDetailsDao = productDetailsDao;
    }

    @Override
    public Single<List<Product>> getProducts() {
        return productDao.getProducts()
                .doOnSuccess(productEntities -> {
                    if (productEntities.isEmpty()) {
                        throw new Resources.NotFoundException();
                    }
                })
                .toObservable()
                .flatMapIterable(productEntities -> productEntities)
                .cast(Product.class)
                .toList();
    }

    @Override
    public Single<ProductDetails> getDetailsFor(Product product) {
        return productDetailsDao.getProductDetails(product.getId())
                .cast(ProductDetails.class);
    }

    @Override
    public void saveProducts(List<Product> products) {
        Observable.fromIterable(products)
                .map(product -> new ProductEntity.Builder()
                        .id(product.getId())
                        .imageUrl(product.getImageUrl())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build())
                .toList()
                .subscribe(productDao::saveProducts, Timber::e);
    }

    @Override
    public void saveDetailsFor(ProductDetails productDetails) {
        productDetailsDao.saveProductDetails((ProductDetailsEntity) productDetails);
    }
}
