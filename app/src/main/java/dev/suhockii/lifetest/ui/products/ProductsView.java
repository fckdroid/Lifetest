package dev.suhockii.lifetest.ui.products;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import javax.annotation.Nullable;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.ui.details.DetailsFragment;
import dev.suhockii.lifetest.util.mvp.ClearShowingSnackbarStrategy;
import dev.suhockii.lifetest.util.ui.SnackbarView;

public interface ProductsView extends MvpView, SnackbarView{

    /**
     * Shows product list on UI.
     *
     * @param products content for show.
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProducts(List<Product> products);
}
