package dev.suhockii.lifetest.ui.details;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;
import javax.inject.Named;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.model.ProductDetails;
import dev.suhockii.lifetest.repo.AppRepository;
import dev.suhockii.lifetest.util.mvp.NonLeakPresenter;
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers;
import io.reactivex.Single;
import timber.log.Timber;

import static dev.suhockii.lifetest.di.module.RepositoryModule.LOCAL_REPOSITORY;
import static dev.suhockii.lifetest.di.module.RepositoryModule.REMOTE_REPOSITORY;

@InjectViewState
public class DetailsPresenter extends NonLeakPresenter<DetailsView> {

    private final AppRepository localRepository;
    private final AppRepository remoteRepository;
    private final RxSchedulers rxSchedulers;

    @Inject
    public DetailsPresenter(@Named(LOCAL_REPOSITORY) AppRepository localRepository,
                            @Named(REMOTE_REPOSITORY) AppRepository remoteRepository,
                            RxSchedulers rxSchedulers) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().onFirstPresenterAttach();
    }

    void loadDetailsFor(Product product) {
        getViewState().showProgressBar(View.VISIBLE);

        localRepository.getDetailsFor(product)
                .onErrorResumeNext(localEmpty -> updateDetailsFor(product))
                .doOnSubscribe(compositeDisposable::add)
                .compose(rxSchedulers.getIoToMainTransformerSingle())
                .doOnSuccess(productDetails -> getViewState().clearShowSnackbarCommand())
                .doOnError(throwable -> getViewState().showSnackbar(throwable.getMessage(), () -> loadDetailsFor(product)))
                .doOnEvent((productDetails, throwable) -> getViewState().showProgressBar(View.INVISIBLE))
                .subscribe(productDetails -> getViewState().showDetails(productDetails), Timber::e);
    }

    private Single<ProductDetails> updateDetailsFor(Product product) {
        return remoteRepository.getDetailsFor(product)
                .doOnSuccess(localRepository::saveProductDetails);
    }
}
