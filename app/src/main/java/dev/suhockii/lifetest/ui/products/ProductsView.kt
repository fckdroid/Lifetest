package dev.suhockii.lifetest.ui.products


import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.util.mvp.SnackbarView
import dev.suhockii.lifetest.util.ui.Visibility

interface ProductsView : MvpView, SnackbarView {

    /**
     * Shows product list on UI.
     *
     * @param products content for show.
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressBar(@Visibility visible: Int)
}
