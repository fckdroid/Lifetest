package dev.suhockii.lifetest.ui.list;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.ui.BaseFragment;

public class ListFragment extends BaseFragment implements ListView {
    @InjectPresenter
    ListPresenter listPresenter;

    private View rvProducts;

    @NonNull
    @Override
    protected String getToolbarTitle() {
        return "List Fragment";
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
    }
}
