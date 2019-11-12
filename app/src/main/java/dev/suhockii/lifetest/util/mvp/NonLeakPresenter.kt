package dev.suhockii.lifetest.util.mvp

import moxy.MvpPresenter
import moxy.MvpView

import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

abstract class NonLeakPresenter<V : MvpView> : MvpPresenter<V>() {

    protected var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}