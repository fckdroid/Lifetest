package dev.suhockii.lifetest.ui.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import dev.suhockii.lifetest.model.ProductDetails;
import dev.suhockii.lifetest.util.ui.SnackbarView;

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
}
