package dev.suhockii.lifetest.ui.products

import android.view.View
import dev.suhockii.lifetest.di.module.RepositoryModule
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.repo.AppRepository
import dev.suhockii.lifetest.util.mvp.NonLeakPresenter
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers
import io.reactivex.Single
import moxy.InjectViewState
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class ProductsPresenter @Inject constructor(
    @param:Named(RepositoryModule.LOCAL_REPOSITORY) private val localRepository: AppRepository,
    @param:Named(RepositoryModule.REMOTE_REPOSITORY) private val remoteRepository: AppRepository,
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
            .doOnError { throwable ->viewState.showSnackbar(throwable.message, Runnable { this.loadProducts() })}
            .subscribe ({ products -> viewState.showProducts(products) }, Timber::e)
    }

    private fun updateProducts(): Single<List<Product>> {
        return remoteRepository.products
            .doOnSuccess { localRepository.saveProducts(it) }
    }
}
