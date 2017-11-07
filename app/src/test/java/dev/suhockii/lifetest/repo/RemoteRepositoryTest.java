package dev.suhockii.lifetest.repo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.ArrayList;

import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.data.local.entity.ProductEntity;
import dev.suhockii.lifetest.data.remote.ProductsApi;
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse;
import dev.suhockii.lifetest.data.remote.entity.ProductListResponse;
import dev.suhockii.lifetest.model.Product;
import io.reactivex.Observable;
import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class RemoteRepositoryTest {
    private ProductsApi productsApi;
    private AppRepository remoteRepository;

    @Before
    public void setUp() throws Exception {
        productsApi = Mockito.mock(ProductsApi.class);
        remoteRepository = new RemoteRepository(productsApi);
    }

    @Test
    public void getProducts() throws Exception {
        when(productsApi.getProducts()).thenReturn(Observable.just(new ProductListResponse()));

        remoteRepository.getProducts();

        verify(productsApi, times(1)).getProducts();
    }

    @Test
    public void getDetailsFor() throws Exception {
        String productId = "1";
        Product product = new ProductEntity(productId);
        Single<ProductDetailsResponse> detailsResponse =
                Single.just(new ProductDetailsResponse(productId));

        when(productsApi.getProductDetails(product.getId())).thenReturn(detailsResponse);

        remoteRepository.getDetailsFor(product);

        verify(productsApi, times(1)).getProductDetails(productId);
    }

}