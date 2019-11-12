package dev.suhockii.lifetest.ui.products;


import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

import java.util.List;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.util.mvp.SnackbarView;
import dev.suhockii.lifetest.util.ui.Visibility;

public interface ProductsView extends MvpView, SnackbarView{

    /**
     * Shows product list on UI.
     *
     * @param products content for show.
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProducts(List<Product> products);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgressBar(@Visibility int visible);
}
