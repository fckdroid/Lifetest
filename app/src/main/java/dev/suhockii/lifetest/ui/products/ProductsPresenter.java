package dev.suhockii.lifetest.ui.products;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.repo.AppRepository;
import dev.suhockii.lifetest.util.ui.NonLeakPresenter;
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers;
import io.reactivex.Single;
import timber.log.Timber;

import static dev.suhockii.lifetest.di.module.RepositoryModule.LOCAL_REPOSITORY;
import static dev.suhockii.lifetest.di.module.RepositoryModule.REMOTE_REPOSITORY;

@InjectViewState
public class ProductsPresenter extends NonLeakPresenter<ProductsView> {

    private final AppRepository localRepository;
    private final AppRepository remoteRepository;
    private final RxSchedulers rxSchedulers;

    @Inject
    public ProductsPresenter(@Named(LOCAL_REPOSITORY) AppRepository localRepository,
                             @Named(REMOTE_REPOSITORY) AppRepository remoteRepository,
                             RxSchedulers rxSchedulers) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadProducts();
    }

    public void loadProducts() {
        localRepository.getProducts()
                .onErrorResumeNext(localEmpty -> updateProducts())
                .doOnSubscribe(compositeDisposable::add)
                .compose(rxSchedulers.getIoToMainTransformerSingle())
                .doOnError(throwable -> getViewState().showSnackbar(
                        throwable.getMessage(), this::loadProducts))
                .doOnSuccess(products -> getViewState().clearShowSnackbarCommand())
                .subscribe(products -> getViewState().showProducts(products), Timber::e);
    }

    private Single<List<Product>> updateProducts() {
        return remoteRepository.getProducts()
                .doOnSuccess(localRepository::saveProducts);
    }
}
