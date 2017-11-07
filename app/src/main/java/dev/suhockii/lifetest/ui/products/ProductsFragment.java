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

import javax.inject.Inject;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.di.AppInjector;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.util.ui.FragmentRouter;
import dev.suhockii.lifetest.util.ui.fragment.SnackbarFragment;
import dev.suhockii.lifetest.util.ui.layout.AutofitRecyclerView;

public class ProductsFragment extends SnackbarFragment implements ProductsView {

    @InjectPresenter
    ProductsPresenter productsPresenter;

    @Inject
    FragmentRouter fragmentRouter;

    @ProvidePresenter
    ProductsPresenter provideProductsPresenter() {
        return AppInjector.getAppComponent().getProductsPresenter();
    }

    private AutofitRecyclerView rvProducts;

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.fragment_products_title);
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
                .setOnProductClickListener(this::onProductClick);
        rvProducts.setAdapter(adapter);
    }

    private void onProductClick(View view) {
        int clickedPos = rvProducts.getLayoutManager().getPosition(view);
        ProductsRecyclerAdapter productsRecyclerAdapter =
                (ProductsRecyclerAdapter) rvProducts.getAdapter();
        Product clickedProduct = productsRecyclerAdapter.getProductAt(clickedPos);

        fragmentRouter.openDetailsFragment(view, clickedProduct);
    }
}
