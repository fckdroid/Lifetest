package dev.suhockii.lifetest.ui.details

import android.view.View

import moxy.InjectViewState

import javax.inject.Inject
import javax.inject.Named

import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.model.ProductDetails
import dev.suhockii.lifetest.repo.AppRepository
import dev.suhockii.lifetest.util.mvp.NonLeakPresenter
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers
import io.reactivex.Single
import timber.log.Timber

import dev.suhockii.lifetest.di.module.RepositoryModule.LOCAL_REPOSITORY
import dev.suhockii.lifetest.di.module.RepositoryModule.REMOTE_REPOSITORY

@InjectViewState
class DetailsPresenter @Inject constructor(
    @param:Named(LOCAL_REPOSITORY) private val localRepository: AppRepository,
    @param:Named(REMOTE_REPOSITORY) private val remoteRepository: AppRepository,
    private val rxSchedulers: RxSchedulers
) : NonLeakPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.onFirstPresenterAttach()
    }

    internal fun loadDetailsFor(product: Product) {
        viewState.showProgressBar(View.VISIBLE)

        localRepository.getDetailsFor(product)
            .onErrorResumeNext { updateDetailsFor(product) }
            .doOnSubscribe{ compositeDisposable.add(it) }
            .compose(rxSchedulers.getIoToMainTransformerSingle())
            .doOnSuccess { viewState.clearShowSnackbarCommand() }
            .doOnError { throwable -> viewState.showSnackbar(throwable.message, Runnable { loadDetailsFor(product) }) }
            .doOnEvent { _, throwable ->
                Timber.e(throwable)
                viewState.showProgressBar(View.INVISIBLE) }
            .subscribe { productDetails -> viewState.showDetails(productDetails) }
    }

    private fun updateDetailsFor(product: Product): Single<ProductDetails> {
        return remoteRepository.getDetailsFor(product)
            .doOnSuccess { localRepository.saveProductDetails(it) }
    }
}
