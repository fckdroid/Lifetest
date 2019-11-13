package dev.suhockii.lifetest.ui.details

import android.content.res.Resources
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity
import dev.suhockii.lifetest.data.local.entity.ProductEntity
import dev.suhockii.lifetest.data.remote.entity.ProductDetailsResponse
import dev.suhockii.lifetest.model.ProductDetails
import dev.suhockii.lifetest.repo.AppRepository
import dev.suhockii.lifetest.repo.LocalRepository
import dev.suhockii.lifetest.repo.RemoteRepository
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers
import io.reactivex.Single
import io.reactivex.SingleTransformer
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.*

class DetailsPresenterTest {
    private var detailsView: DetailsView? = null
    private var detailsPresenter: DetailsPresenter? = null
    private var localRepository: AppRepository? = null
    private var remoteRepository: AppRepository? = null
    private var rxSchedulers: RxSchedulers? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        detailsView = mock(DetailsView::class.java)
        rxSchedulers = mock(RxSchedulers::class.java)
        localRepository = mock(LocalRepository::class.java)
        remoteRepository = mock(RemoteRepository::class.java)

        detailsPresenter = DetailsPresenter(localRepository!!, remoteRepository!!, rxSchedulers!!)
        detailsPresenter!!.attachView(detailsView)
    }

    @Test
    @Throws(Exception::class)
    fun onFirstViewAttach() {
        verify<DetailsView>(detailsView, times(1)).onFirstPresenterAttach()
    }

    @Test
    @Throws(Exception::class)
    fun loadDetailsFromLocal() {
        val productId = "1"
        val product = ProductEntity(productId)
        val productDetails = ProductDetailsEntity(productId)

        `when`<SingleTransformer<Any, Any>>(rxSchedulers!!.getIoToMainTransformerSingle()).thenReturn(
            SingleTransformer { upstream -> upstream })
        `when`<Single<ProductDetails>>(localRepository!!.getDetailsFor(product)).thenReturn(
            Single.just<ProductDetails>(
                productDetails
            )
        )

        detailsPresenter!!.loadDetailsFor(product)

        verify<AppRepository>(localRepository, times(1)).getDetailsFor(product)
        verify<DetailsView>(detailsView, times(1)).showDetails(productDetails)
    }

    @Test
    @Throws(Exception::class)
    fun loadDetailsFromRemote_NetworkConnected() {
        val productId = "1"
        val product = ProductEntity(productId)
        val productDetails = ProductDetailsResponse(productId)

        `when`<SingleTransformer<Any, Any>>(rxSchedulers!!.getIoToMainTransformerSingle()).thenReturn(
            SingleTransformer { upstream -> upstream })
        `when`<Single<ProductDetails>>(localRepository!!.getDetailsFor(product)).thenReturn(
            Single.error<ProductDetails>(
                Resources.NotFoundException()
            )
        )
        `when`<Single<ProductDetails>>(remoteRepository!!.getDetailsFor(product)).thenReturn(
            Single.just<ProductDetails>(
                productDetails
            )
        )

        detailsPresenter!!.loadDetailsFor(product)

        verify<AppRepository>(localRepository, times(1)).getDetailsFor(product)
        verify<AppRepository>(remoteRepository, times(1)).getDetailsFor(product)
        verify<AppRepository>(localRepository, times(1)).saveProductDetails(productDetails)
        verify<DetailsView>(detailsView, times(1)).showDetails(productDetails)
    }

    @Test
    @Throws(Exception::class)
    fun loadDetailsFromRemote_NetworkDiconnected() {
        val productId = "1"
        val message = "Error message."
        val product = ProductEntity(productId)

        `when`<SingleTransformer<Any, Any>>(rxSchedulers!!.getIoToMainTransformerSingle()).thenReturn(
            SingleTransformer { upstream -> upstream })
        `when`<Single<ProductDetails>>(localRepository!!.getDetailsFor(product)).thenReturn(
            Single.error<ProductDetails>(
                Resources.NotFoundException()
            )
        )
        `when`<Single<ProductDetails>>(remoteRepository!!.getDetailsFor(product)).thenReturn(
            Single.error<ProductDetails>(
                Exception(message)
            )
        )

        detailsPresenter!!.loadDetailsFor(product)

        verify<AppRepository>(localRepository, times(1)).getDetailsFor(product)
        verify<AppRepository>(remoteRepository, times(1)).getDetailsFor(product)
        verify<DetailsView>(detailsView, times(1)).showSnackbar(eq(message), any<Runnable>())
    }
}