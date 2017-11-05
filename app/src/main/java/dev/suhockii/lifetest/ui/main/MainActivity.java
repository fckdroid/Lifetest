package dev.suhockii.lifetest.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.ui.InjectableActivity;
import dev.suhockii.lifetest.ui.BaseFragment;
import dev.suhockii.lifetest.ui.FragmentRouter;

public class MainActivity extends InjectableActivity implements MainView,
        BaseFragment.OnFragmentInteractionListener {

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
            fragmentRouter.openListFragment();
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
}
