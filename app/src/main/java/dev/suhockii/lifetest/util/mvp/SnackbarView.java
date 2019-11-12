package dev.suhockii.lifetest.util.mvp;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

import java.util.List;

import javax.annotation.Nullable;

import dev.suhockii.lifetest.model.Product;
import dev.suhockii.lifetest.util.mvp.ClearShowingSnackbarStrategy;

public interface SnackbarView extends MvpView {
    /**
     * This method helps to handle different exceptions by sending
     * readable description of what went wrong to user.
     *
     * @param message  it is a readable description of what went wrong.
     * @param runnable it is method that program should rerun if user will agree.
     * @see ClearShowingSnackbarStrategy
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showSnackbar(@Nullable String message, @Nullable Runnable runnable);

    /**
     * The {@link StateStrategyType} require this method to stay alive after screen
     * rotation. That`s why we should be able to stop showing snackbar to user when data is
     * load successfully. {@link ClearShowingSnackbarStrategy} helps with it.
     */
    @StateStrategyType(ClearShowingSnackbarStrategy.class)
    void clearShowSnackbarCommand();
}
