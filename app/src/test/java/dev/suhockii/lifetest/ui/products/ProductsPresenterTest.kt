package dev.suhockii.lifetest.ui.products

import android.content.res.Resources
import dev.suhockii.lifetest.model.Product
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
import java.util.*

class ProductsPresenterTest {
    private var productsView: ProductsView? = null
    private var productsPresenter: ProductsPresenter? = null
    private var localRepository: AppRepository? = null
    private var remoteRepository: AppRepository? = null
    private var rxSchedulers: RxSchedulers? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        productsView = mock(ProductsView::class.java)
        rxSchedulers = mock(RxSchedulers::class.java)
        localRepository = mock(LocalRepository::class.java)
        remoteRepository = mock(RemoteRepository::class.java)

        productsPresenter = ProductsPresenter(localRepository!!, remoteRepository!!, rxSchedulers!!)
    }

    @Test
    fun onFirstViewAttachLoadLocal() {
        val products = ArrayList<Product>()
        `when`(rxSchedulers!!.getIoToMainTransformerSingle<Any>()).thenReturn(SingleTransformer { upstream -> upstream })
        `when`(localRepository!!.getProducts()).thenReturn(Single.just(products))

        productsPresenter!!.attachView(productsView)

        verify<AppRepository>(localRepository, times(1)).getProducts()
        verify<ProductsView>(productsView, times(1)).clearShowSnackbarCommand()
        verify<ProductsView>(productsView, times(1)).showProducts(products)
    }

    @Test
    @Throws(Exception::class)
    fun onFirstViewAttachLoadRemote_NetworkConnected() {
        val products = ArrayList<Product>()
        `when`(rxSchedulers!!.getIoToMainTransformerSingle<Any>()).thenReturn(SingleTransformer { upstream -> upstream })
        `when`(localRepository!!.getProducts()).thenReturn(Single.error(Exception()))
        `when`(remoteRepository!!.getProducts()).thenReturn(Single.just(products))

        productsPresenter!!.attachView(productsView)

        verify<AppRepository>(localRepository, times(1)).getProducts()
        verify<AppRepository>(remoteRepository, times(1)).getProducts()
        verify<ProductsView>(productsView, times(1)).clearShowSnackbarCommand()
        verify<ProductsView>(productsView, times(1)).showProducts(products)
    }

    @Test
    @Throws(Exception::class)
    fun onFirstViewAttachLoadRemote_NetworkDisconnected() {
        val message = "Error message."

        `when`(rxSchedulers!!.getIoToMainTransformerSingle<Any>()).thenReturn(SingleTransformer { upstream -> upstream })
        `when`(localRepository!!.getProducts()).thenReturn(Single.error(Resources.NotFoundException()))
        `when`(remoteRepository!!.getProducts()).thenReturn(Single.error(Exception(message)))

        productsPresenter!!.attachView(productsView)

        verify<AppRepository>(localRepository, times(1)).getProducts()
        verify<AppRepository>(remoteRepository, times(1)).getProducts()
        verify<ProductsView>(productsView, times(1)).showSnackbar(eq(message), any<Runnable>())
    }
}