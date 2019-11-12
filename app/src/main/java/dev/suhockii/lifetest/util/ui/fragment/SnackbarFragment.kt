package dev.suhockii.lifetest.util.ui.fragment

import com.google.android.material.snackbar.Snackbar

import dev.suhockii.lifetest.R
import dev.suhockii.lifetest.util.mvp.SnackbarView

abstract class SnackbarFragment : NonLeakFragment(), SnackbarView {

    private var snackbar: Snackbar? = null

    override fun showSnackbar(message: String?, runnable: Runnable?) {
        if (view == null) {
            return
        }
        snackbar = Snackbar.make(view!!, getString(R.string.network_error) + message!!, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.try_again)) { view -> runnable!!.run() }
        snackbar!!.show()
    }

    override fun clearShowSnackbarCommand() { /*ignore*/
    }

    override fun onPause() {
        super.onPause()
        if (snackbar != null && snackbar!!.isShown) {
            snackbar!!.dismiss()
        }
    }
}
