package dev.suhockii.lifetest.ui.products

import android.os.Bundle
import androidx.annotation.LayoutRes

import android.view.View

import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

import javax.inject.Inject

import dev.suhockii.lifetest.R
import dev.suhockii.lifetest.di.AppInjector
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.util.ui.FragmentRouter
import dev.suhockii.lifetest.util.ui.Visibility
import dev.suhockii.lifetest.util.ui.fragment.SnackbarFragment
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : SnackbarFragment(), ProductsView {

    @InjectPresenter
    lateinit var productsPresenter: ProductsPresenter

    @Inject
    lateinit var fragmentRouter: FragmentRouter

    override val toolbarTitle: String
        get() = getString(R.string.fragment_products_title)

    override val layoutRes: Int
        @LayoutRes
        get() = R.layout.fragment_products

    @ProvidePresenter
    fun provideProductsPresenter(): ProductsPresenter {
        return AppInjector.getAppComponent().productsPresenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsRecyclerView.setHasFixedSize(true)
    }

    override fun showProgressBar(@Visibility visible: Int) {
        productsProgressBar.visibility = visible
    }

    private fun onProductClick(view: View) {
        val clickedPos = productsRecyclerView.layoutManager!!.getPosition(view)
        val productsRecyclerAdapter = productsRecyclerView.adapter as ProductsRecyclerAdapter?
        val clickedProduct = productsRecyclerAdapter!!.getProductAt(clickedPos)

        fragmentRouter.openDetailsFragment(view, clickedProduct)
    }

    override fun showProducts(products: List<Product>) {
        val adapter = ProductsRecyclerAdapter(products)
            .setOnProductClickListener(object : ProductsRecyclerAdapter.OnProductClickListener {
                override fun onItemClick(view: View) {
                    this@ProductsFragment.onProductClick(view)
                }
            })
        productsRecyclerView.adapter = adapter
    }
}
