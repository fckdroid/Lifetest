package dev.suhockii.lifetest.repo;

import com.facebook.stetho.inspector.elements.Document;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity;
import dev.suhockii.lifetest.data.local.entity.ProductEntity;
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class LocalRepositoryTest {
    private ProductDao productDao;
    private ProductDetailsDao productDetailsDao;
    private AppRepository localRepository;

    @Before
    public void setUp() throws Exception {
        productDao = Mockito.mock(ProductDao.class);
        productDetailsDao = Mockito.mock(ProductDetailsDao.class);
        localRepository = new LocalRepository(productDao, productDetailsDao);
    }

    @Test
    public void getProducts() throws Exception {
        when(productDao.getProducts()).thenReturn(Single.just(new ArrayList<>()));

        localRepository.getProducts();

        verify(productDao, times(1)).getProducts();
    }

    @Test
    public void getDetailsFor() throws Exception {
        String productId = "1";
        ProductDetailsEntity productDetails = new ProductDetailsEntity(productId);
        Product product = new ProductEntity(productId);

        when(productDetailsDao.getProductDetails(productId)).thenReturn(Single.just(productDetails));

        localRepository.getDetailsFor(product);

        verify(productDetailsDao, times(1)).getProductDetails(productId);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void saveProducts() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        List<ProductEntity> productEntities = (List<ProductEntity>) (List<?>) products;

        localRepository.saveProducts(products);

        verify(productDao, times(1)).saveProducts(productEntities);
    }

    @Test
    public void saveProductDetails() throws Exception {
        String productId = "1";
        ProductDetails productDetails = new ProductDetailsResponse(productId);
        ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity.Builder()
                .id(productDetails.getId())
                .build();

        localRepository.saveProductDetails(productDetails);

        verify(productDetailsDao, times(1)).saveProductDetails(productDetailsEntity);
    }

}