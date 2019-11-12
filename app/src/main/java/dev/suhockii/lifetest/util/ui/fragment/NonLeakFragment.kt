package dev.suhockii.lifetest.util.ui.fragment

import dev.suhockii.lifetest.util.mvp.SnackbarView
import io.reactivex.disposables.CompositeDisposable

abstract class NonLeakFragment : InteractionFragment(), SnackbarView {

    private var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun getCompositeDisposable() = compositeDisposable
}
