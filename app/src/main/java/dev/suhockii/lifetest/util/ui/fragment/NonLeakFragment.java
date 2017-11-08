package dev.suhockii.lifetest.util.ui.fragment;

import dev.suhockii.lifetest.util.mvp.SnackbarView;
import io.reactivex.disposables.CompositeDisposable;

public abstract class NonLeakFragment extends InteractionFragment implements SnackbarView {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
