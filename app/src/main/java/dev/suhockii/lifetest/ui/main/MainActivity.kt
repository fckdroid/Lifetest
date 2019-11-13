package dev.suhockii.lifetest.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import dev.suhockii.lifetest.R
import dev.suhockii.lifetest.util.ui.FragmentRouter
import dev.suhockii.lifetest.util.ui.activity.InjectableActivity
import dev.suhockii.lifetest.util.ui.fragment.InteractionFragment
import javax.inject.Inject

class MainActivity : InjectableActivity(), InteractionFragment.OnFragmentInteractionListener {

    @Inject
    lateinit var fragmentRouter: FragmentRouter

    val containerId: Int
        @IdRes
        get() = R.id.main_fl_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentRouter.openListFragment()
        }
    }

    override fun onFragmentInteraction(title: String, showBackButton: Boolean) {
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(showBackButton)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
