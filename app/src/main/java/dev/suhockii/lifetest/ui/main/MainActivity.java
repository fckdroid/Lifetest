package dev.suhockii.lifetest.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

import javax.inject.Inject;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.util.ui.activity.InjectableActivity;
import dev.suhockii.lifetest.util.ui.fragment.InteractionFragment;
import dev.suhockii.lifetest.util.ui.FragmentRouter;

public class MainActivity extends InjectableActivity implements MainView,
        InteractionFragment.OnFragmentInteractionListener {

    @InjectPresenter
    MainPresenter mainPresenter;

    @Inject
    FragmentRouter fragmentRouter;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        if (savedInstanceState == null) {
            if (fragmentRouter == null) {
                Toast.makeText(this, "fragmentRouter null", Toast.LENGTH_SHORT).show();
            } else {
                fragmentRouter.openListFragment();
            }
        }
    }

    @IdRes
    public int getContainerId() {
        return R.id.main_fl_container;
    }

    @Override
    public void onFragmentInteraction(String title) {
        actionBar.setTitle(title);
//        actionBar.setDisplayHomeAsUpEnabled(isDrawerLocked);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
