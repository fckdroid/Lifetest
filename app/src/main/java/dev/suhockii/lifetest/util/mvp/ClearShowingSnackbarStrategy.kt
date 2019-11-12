package dev.suhockii.lifetest.util.mvp

import moxy.MvpView
import moxy.viewstate.ViewCommand
import moxy.viewstate.strategy.StateStrategy

import dev.suhockii.lifetest.ui.products.ProductsView

/**
 * This strategy was developed to clear "showSnackbar" command in [ProductsView]
 */
class ClearShowingSnackbarStrategy : StateStrategy {

    override fun <View : MvpView> beforeApply(
        currentState: MutableList<ViewCommand<View>>,
        incomingCommand: ViewCommand<View>
    ) {
        val iterator = currentState.iterator()

        while (iterator.hasNext()) {
            val entry = iterator.next()


            if (entry.tag == SHOW_SNACKBAR) {
                iterator.remove()
            }
        }
    }

    override fun <View : MvpView> afterApply(
        currentState: List<ViewCommand<View>>,
        incomingCommand: ViewCommand<View>
    ) {
        /*Do nothing*/
    }

    companion object {
        private const val SHOW_SNACKBAR = "showSnackbar"
    }
}
