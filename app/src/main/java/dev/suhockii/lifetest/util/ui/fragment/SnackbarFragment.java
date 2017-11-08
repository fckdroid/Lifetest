package dev.suhockii.lifetest.util.ui.fragment;

import android.support.design.widget.Snackbar;

import javax.annotation.Nullable;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.util.mvp.SnackbarView;

public abstract class SnackbarFragment extends NonLeakFragment implements SnackbarView {

    @Nullable
    private Snackbar snackbar;

    @Override
    public void showSnackbar(String message, Runnable runnable) {
        if (getView() == null) {
            return;
        }
        snackbar = Snackbar.make(getView(), getString(R.string.network_error) + message, Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.try_again), view -> runnable.run());
        snackbar.show();
    }

    @Override
    public void clearShowSnackbarCommand() { /*ignore*/ }

    @Override
    public void onPause() {
        super.onPause();
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }
}
