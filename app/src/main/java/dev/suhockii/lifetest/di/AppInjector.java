package dev.suhockii.lifetest.di;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import dev.suhockii.lifetest.App;

public class AppInjector {

    private static AppComponent appComponent;

    private AppInjector() { }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static void init(App app) {
        appComponent = DaggerAppComponent.builder()
                .application(app)
                .build();
        appComponent.inject(app);

        app.registerActivityLifecycleCallbacks(new ActivityCreationListener() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                handleActivity(activity);
            }
        });
    }

    private static void handleActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector) {
            AndroidInjection.inject(activity);
        }

        if (activity instanceof AppCompatActivity) {
            FragmentManager.FragmentLifecycleCallbacks lifecycleCallbacks =
                    new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentCreated(FragmentManager fm, Fragment f,
                                                      Bundle savedInstanceState) {
                            handleFragment(f);
                        }
                    };

            ((AppCompatActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(lifecycleCallbacks, true);
        }
    }

    private static void handleFragment(Fragment f) {
        if (f instanceof Injectable) {
            AndroidSupportInjection.inject(f);
        }
    }
}

