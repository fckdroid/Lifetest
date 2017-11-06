package dev.suhockii.lifetest.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dev.suhockii.lifetest.ui.details.DetailsFragment;
import dev.suhockii.lifetest.ui.products.ProductsFragment;
import dev.suhockii.lifetest.ui.main.MainActivity;

public final class FragmentRouter {
    @IdRes
    private final int containerId;

    private final FragmentManager fragmentManager;

    @Inject
    FragmentRouter(MainActivity mainActivity) {
        this.containerId = mainActivity.getContainerId();
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void openListFragment() {
        ProductsFragment fragment = new ProductsFragment();
        replaceBy(fragment);
    }

    public void openDetailsFragment() {
        DetailsFragment fragment = new DetailsFragment();
        replaceBy(fragment);
    }

    private void replaceBy(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
