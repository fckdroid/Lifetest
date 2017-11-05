package dev.suhockii.lifetest.ui.details;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpAppCompatFragment;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.ui.BaseFragment;

public class DetailsFragment extends BaseFragment implements DetailsView {
    @NonNull
    @Override
    protected String getToolbarTitle() {
        return "Details Fragment";
    }

    @Override
    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.fragment_details;
    }
}
