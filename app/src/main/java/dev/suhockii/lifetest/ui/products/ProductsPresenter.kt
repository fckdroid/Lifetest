package dev.suhockii.lifetest.ui.products

import android.view.View

import moxy.InjectViewState

import javax.inject.Inject
import javax.inject.Named

import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.repo.AppRepository
import dev.suhockii.lifetest.util.mvp.NonLeakPresenter
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers
import io.reactivex.Single
import timber.log.Timber

import dev.suhockii.lifetest.di.module.RepositoryModule.LOCAL_REPOSITORY
import dev.suhockii.lifetest.di.module.RepositoryModule.REMOTE_REPOSITORY
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

@InjectViewState
class ProductsPresenter @Inject constructor(
    @param:Named(LOCAL_REPOSITORY) private val localRepository: AppRepository,
    @param:Named(REMOTE_REPOSITORY) private val remoteRepository: AppRepository,
    private val rxSchedulers: RxSchedulers
) : NonLeakPresenter<ProductsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadProducts()
    }

    private fun loadProducts() {
        viewState.showProgressBar(View.VISIBLE)

        localRepository.products
            .onErrorResumeNext { updateProducts() }
            .compose(rxSchedulers.getIoToMainTransformerSingle())
            .doOnSubscribe { compositeDisposable.add(it) }
            .doOnEvent { _, _ -> viewState.showProgressBar(View.INVISIBLE) }
            .doOnSuccess { viewState.clearShowSnackbarCommand() }
            .doOnError { throwable ->
                Timber.e(throwable)
                viewState.showSnackbar(throwable.message, Runnable { this.loadProducts() })
            }
            .subscribe { products -> viewState.showProducts(products) }
    }

    private fun updateProducts(): Single<List<Product>> {
        return remoteRepository.products
            .doOnSuccess { localRepository.saveProducts(it) }
    }
}
