package dev.suhockii.lifetest.util.mvp;

import moxy.MvpPresenter;
import moxy.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public abstract class NonLeakPresenter<V extends MvpView> extends MvpPresenter<V> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}