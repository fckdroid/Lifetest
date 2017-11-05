package dev.suhockii.lifetest.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import dev.suhockii.lifetest.App;
import dev.suhockii.lifetest.di.component.DaggerAppComponent;

public class AppInjector {
    private AppInjector() { }

    public static void init(App app) {
        DaggerAppComponent.builder()
                .application(app)
                .build()
                .inject(app);

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

