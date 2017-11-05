package dev.suhockii.lifetest.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public abstract class ActivityCreationListener implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityStarted(Activity activity) { /*ignore*/ }

    @Override
    public void onActivityResumed(Activity activity) { /*ignore*/ }

    @Override
    public void onActivityPaused(Activity activity) { /*ignore*/ }

    @Override
    public void onActivityStopped(Activity activity) { /*ignore*/ }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) { /*ignore*/ }

    @Override
    public void onActivityDestroyed(Activity activity) { /*ignore*/ }
}
