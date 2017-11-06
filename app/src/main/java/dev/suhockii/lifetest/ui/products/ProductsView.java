package dev.suhockii.lifetest.ui.products;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import dev.suhockii.lifetest.model.Product;

@StateStrategyType(SkipStrategy.class)
public interface ProductsView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProducts(List<Product> products);
}
