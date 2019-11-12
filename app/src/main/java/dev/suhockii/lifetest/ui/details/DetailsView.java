package dev.suhockii.lifetest.ui.details;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

import dev.suhockii.lifetest.model.ProductDetails;
import dev.suhockii.lifetest.util.mvp.SnackbarView;
import dev.suhockii.lifetest.util.ui.Visibility;

public interface DetailsView extends MvpView, SnackbarView {
    /**
     * Shows {@link ProductDetails} on UI.
     *
     * @param productDetails content for show.
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showDetails(ProductDetails productDetails);

    @StateStrategyType(SkipStrategy.class)
    void onFirstPresenterAttach();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgressBar(@Visibility int visible);
}
