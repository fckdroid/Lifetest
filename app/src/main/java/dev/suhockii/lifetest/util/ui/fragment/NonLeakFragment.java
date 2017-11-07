package dev.suhockii.lifetest.util.ui.fragment;

import android.support.design.widget.Snackbar;

import javax.annotation.Nullable;

import dev.suhockii.lifetest.R;
import dev.suhockii.lifetest.util.ui.SnackbarView;
import io.reactivex.disposables.CompositeDisposable;

public abstract class NonLeakFragment extends InteractionFragment implements SnackbarView {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
