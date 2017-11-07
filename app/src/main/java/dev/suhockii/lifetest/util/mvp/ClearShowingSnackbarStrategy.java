package dev.suhockii.lifetest.util.mvp;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

import java.util.Iterator;
import java.util.List;

import dev.suhockii.lifetest.ui.products.ProductsView;

/**
 * This strategy was developed to clear "showSnackbar" command in {@link ProductsView}
 */
public class ClearShowingSnackbarStrategy implements StateStrategy {

    private static final String SHOW_SNACKBAR = "showSnackbar";

    @Override
    public <View extends MvpView> void beforeApply(List<ViewCommand<View>> currentState,
                                                   ViewCommand<View> incomingCommand) {
        Iterator<ViewCommand<View>> iterator = currentState.iterator();

        while (iterator.hasNext()) {
            ViewCommand<View> entry = iterator.next();

            //noinspection StringEquality
            if (entry.getTag().equals(SHOW_SNACKBAR)) {
                iterator.remove();
            }
        }
    }

    @Override
    public <View extends MvpView> void afterApply(List<ViewCommand<View>> currentState,
                                                  ViewCommand<View> incomingCommand) {
        /*Do nothing*/
    }
}
