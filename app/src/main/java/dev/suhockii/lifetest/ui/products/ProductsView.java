package dev.suhockii.lifetest.ui.products;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

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

    void showProgressBar(@Visibility int visible);
}
