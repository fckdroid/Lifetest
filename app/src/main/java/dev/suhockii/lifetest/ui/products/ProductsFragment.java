package dev.suhockii.lifetest.ui.products;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.di.AppInjector;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.ui.BaseFragment;
import dev.suhockii.lifetest.util.AutofitRecyclerView;

public class ProductsFragment extends BaseFragment implements ProductsView {

    @InjectPresenter
    ProductsPresenter productsPresenter;

    @ProvidePresenter
    ProductsPresenter provideProductsPresenter() {
        return AppInjector.getAppComponent().getProductsPresenter();
    }

    private AutofitRecyclerView rvProducts;

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return "Products Fragment";
    }

    @Override
    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvProducts = view.findViewById(R.id.list_rv_products);
        rvProducts.setHasFixedSize(true);
    }

    @Override
    public void showProducts(List<Product> products) {
        RecyclerView.Adapter adapter = new ProductsRecyclerAdapter(products)
                .setOnItemClickListener(view -> {
                    int clickedPos = rvProducts.getLayoutManager().getPosition(view);
                    ProductsRecyclerAdapter productsRecyclerAdapter =
                            (ProductsRecyclerAdapter) rvProducts.getAdapter();
                    Product clickedProduct = productsRecyclerAdapter.getProductAt(clickedPos);
                    productsPresenter.onProductClicked(clickedProduct);
                });

        rvProducts.setAdapter(adapter);
    }
}
