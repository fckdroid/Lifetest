package dev.suhockii.lifetest.ui.details;

import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;

import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity;
import dev.suhockii.lifetest.data.local.entity.ProductEntity;
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.repo.AppRepository;
import dev.suhockii.lifetest.repo.LocalRepository;
import dev.suhockii.lifetest.repo.RemoteRepository;
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailsPresenterTest {
    private DetailsView detailsView;
    private DetailsPresenter detailsPresenter;
    private AppRepository localRepository;
    private AppRepository remoteRepository;
    private RxSchedulers rxSchedulers;

    @Before
    public void setUp() throws Exception {
        detailsView = mock(DetailsView.class);
        rxSchedulers = mock(RxSchedulers.class);
        localRepository = mock(LocalRepository.class);
        remoteRepository = mock(RemoteRepository.class);

        detailsPresenter = new DetailsPresenter(localRepository, remoteRepository, rxSchedulers);
        detailsPresenter.attachView(detailsView);
    }

    @Test
    public void onFirstViewAttach() throws Exception {
        verify(detailsView, times(1)).onFirstPresenterAttach();
    }

    @Test
    public void loadDetailsFromLocal() throws Exception {
        String productId = "1";
        Product product = new ProductEntity(productId);
        ProductDetailsEntity productDetails = new ProductDetailsEntity(productId);

        when(rxSchedulers.getIoToMainTransformerSingle()).thenReturn(upstream -> upstream);
        when(localRepository.getDetailsFor(product)).thenReturn(Single.just(productDetails));

        detailsPresenter.loadDetailsFor(product);

        verify(localRepository, times(1)).getDetailsFor(product);
        verify(detailsView, times(1)).showDetails(productDetails);
    }

    @Test
    public void loadDetailsFromRemote_NetworkConnected() throws Exception {
        String productId = "1";
        Product product = new ProductEntity(productId);
        ProductDetailsResponse productDetails = new ProductDetailsResponse(productId);

        when(rxSchedulers.getIoToMainTransformerSingle()).thenReturn(upstream -> upstream);
        when(localRepository.getDetailsFor(product)).thenReturn(Single.error(new Resources.NotFoundException()));
        when(remoteRepository.getDetailsFor(product)).thenReturn(Single.just(productDetails));

        detailsPresenter.loadDetailsFor(product);

        verify(localRepository, times(1)).getDetailsFor(product);
        verify(remoteRepository, times(1)).getDetailsFor(product);
        verify(localRepository, times(1)).saveProductDetails(productDetails);
        verify(detailsView, times(1)).showDetails(productDetails);
    }

    @Test
    public void loadDetailsFromRemote_NetworkDiconnected() throws Exception {
        String productId = "1";
        String message = "Error message.";
        Product product = new ProductEntity(productId);

        when(rxSchedulers.getIoToMainTransformerSingle()).thenReturn(upstream -> upstream);
        when(localRepository.getDetailsFor(product)).thenReturn(Single.error(new Resources.NotFoundException()));
        when(remoteRepository.getDetailsFor(product)).thenReturn(Single.error(new Exception(message)));

        detailsPresenter.loadDetailsFor(product);

        verify(localRepository, times(1)).getDetailsFor(product);
        verify(remoteRepository, times(1)).getDetailsFor(product);
        verify(detailsView, times(1)).showSnackbar(eq(message), any());
    }
}