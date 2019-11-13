package dev.suhockii.lifetest.ui.products;

import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.repo.AppRepository;
import dev.suhockii.lifetest.repo.LocalRepository;
import dev.suhockii.lifetest.repo.RemoteRepository;
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductsPresenterTest {
    private ProductsView productsView;
    private ProductsPresenter productsPresenter;
    private AppRepository localRepository;
    private AppRepository remoteRepository;
    private RxSchedulers rxSchedulers;

    @Before
    public void setUp() throws Exception {
        productsView = mock(ProductsView.class);
        rxSchedulers = mock(RxSchedulers.class);
        localRepository = mock(LocalRepository.class);
        remoteRepository = mock(RemoteRepository.class);

        productsPresenter = new ProductsPresenter(localRepository, remoteRepository, rxSchedulers);
    }

    @Test
    public void onFirstViewAttachLoadLocal() {
        ArrayList<Product> products = new ArrayList<>();
        when(rxSchedulers.getIoToMainTransformerSingle()).thenReturn(upstream -> upstream);
        when(localRepository.getProducts()).thenReturn(Single.just(products));

        productsPresenter.attachView(productsView);

        verify(localRepository, times(1)).getProducts();
        verify(productsView, times(1)).clearShowSnackbarCommand();
        verify(productsView, times(1)).showProducts(products);
    }

    @Test
    public void onFirstViewAttachLoadRemote_NetworkConnected() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        when(rxSchedulers.getIoToMainTransformerSingle()).thenReturn(upstream -> upstream);
        when(localRepository.getProducts()).thenReturn(Single.error(new Exception()));
        when(remoteRepository.getProducts()).thenReturn(Single.just(products));

        productsPresenter.attachView(productsView);

        verify(localRepository, times(1)).getProducts();
        verify(remoteRepository, times(1)).getProducts();
        verify(productsView, times(1)).clearShowSnackbarCommand();
        verify(productsView, times(1)).showProducts(products);
    }

    @Test
    public void onFirstViewAttachLoadRemote_NetworkDisconnected() throws Exception {
        String message = "Error message.";

        when(rxSchedulers.getIoToMainTransformerSingle()).thenReturn(upstream -> upstream);
        when(localRepository.getProducts()).thenReturn(Single.error(new Resources.NotFoundException()));
        when(remoteRepository.getProducts()).thenReturn(Single.error(new Exception(message)));

        productsPresenter.attachView(productsView);

        verify(localRepository, times(1)).getProducts();
        verify(remoteRepository, times(1)).getProducts();
        verify(productsView, times(1)).showSnackbar(eq(message), any());
    }
}