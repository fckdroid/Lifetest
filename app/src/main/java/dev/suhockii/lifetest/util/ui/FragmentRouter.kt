package dev.suhockii.lifetest.util.ui

import android.os.Build
import androidx.annotation.IdRes
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.Fade
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.View

import javax.inject.Inject

import dev.suhockii.lifetest.R
import dev.suhockii.lifetest.model.Product
import dev.suhockii.lifetest.ui.details.DetailsFragment
import dev.suhockii.lifetest.ui.main.MainActivity
import dev.suhockii.lifetest.ui.products.ProductsFragment

/**
 * Simple [FragmentManager] wrapper.
 */
class FragmentRouter @Inject constructor(mainActivity: MainActivity) {

    @IdRes
    private val containerId: Int = mainActivity.containerId

    private val fragmentManager: FragmentManager = mainActivity.supportFragmentManager

    fun openListFragment() {
        val fragment = ProductsFragment()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun openDetailsFragment(view: View, product: Product) {
        val nextFragment = DetailsFragment.newInstance(product)
        val prevFragment = fragmentManager.findFragmentById(containerId)
        prevFragment!!.exitTransition = Fade()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val moving = TransitionInflater.from(view.context)
                .inflateTransition(android.R.transition.move)
            //            moving.setPathMotion(new ArcMotion());

            val transitionSet = TransitionSet()
                .addTransition(moving)
                .addTransition(ChangeTransform())
                .addTransition(ChangeImageTransform())
                .setDuration(TRANSITION_DURATION.toLong())
            nextFragment.sharedElementEnterTransition = transitionSet
        }

        fragmentManager.beginTransaction()
            .addSharedElement(view.findViewById(R.id.item_iv_product), product.id)
            .replace(containerId, nextFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    companion object {
        const val TRANSITION_DURATION = 250
    }
}
