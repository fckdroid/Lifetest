package dev.suhockii.lifetest.util.ui;

import android.os.Build;
import androidx.annotation.IdRes;
import androidx.transition.ArcMotion;
import androidx.transition.ChangeBounds;
import androidx.transition.ChangeImageTransform;
import androidx.transition.ChangeTransform;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;
import androidx.transition.TransitionSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.View;

import javax.inject.Inject;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.ui.details.DetailsFragment;
import dev.suhockii.lifetest.ui.main.MainActivity;
import dev.suhockii.lifetest.ui.products.ProductsFragment;

/**
 * Simple {@link FragmentManager} wrapper.
 */
public final class FragmentRouter {
    public static final int TRANSITION_DURATION = 250;

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

        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void openDetailsFragment(View view, Product product) {
        Fragment nextFragment = DetailsFragment.newInstance(product);
        Fragment prevFragment = fragmentManager.findFragmentById(containerId);
        prevFragment.setExitTransition(new Fade());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition moving = TransitionInflater.from(view.getContext())
                    .inflateTransition(android.R.transition.move);
//            moving.setPathMotion(new ArcMotion());

            TransitionSet transitionSet = new TransitionSet()
                    .addTransition(moving)
                    .addTransition(new ChangeTransform())
                    .addTransition(new ChangeImageTransform())
                    .setDuration(TRANSITION_DURATION);
            nextFragment.setSharedElementEnterTransition(transitionSet);
        }

        fragmentManager.beginTransaction()
                .addSharedElement(view.findViewById(R.id.item_iv_product), product.getId())
                .replace(containerId, nextFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
