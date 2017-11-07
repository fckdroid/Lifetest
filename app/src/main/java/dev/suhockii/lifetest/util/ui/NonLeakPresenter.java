package dev.suhockii.lifetest.util.ui;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

public abstract class NonLeakPresenter<V extends MvpView> extends MvpPresenter<V> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}