package dev.suhockii.lifetest.ui.details

import dev.suhockii.lifetest.model.ProductDetails
import dev.suhockii.lifetest.util.mvp.SnackbarView
import dev.suhockii.lifetest.util.ui.Visibility
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailsView : MvpView, SnackbarView {
    /**
     * Shows [ProductDetails] on UI.
     *
     * @param productDetails content for show.
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showDetails(productDetails: ProductDetails)

    @StateStrategyType(SkipStrategy::class)
    fun onFirstPresenterAttach()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressBar(@Visibility visible: Int)
}
