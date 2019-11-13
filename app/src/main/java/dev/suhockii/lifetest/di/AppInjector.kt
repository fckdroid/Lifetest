package dev.suhockii.lifetest.di

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity

import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dev.suhockii.lifetest.App

object AppInjector {

    var appComponent: AppComponent? = null
        private set

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
        appComponent!!.inject(app)

        app.registerActivityLifecycleCallbacks(object : ActivityCreationListener() {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                handleActivity(activity)
            }
        })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) {
            AndroidInjection.inject(activity)
        }

        if (activity is AppCompatActivity) {
            val lifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                    fm: FragmentManager, f: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    handleFragment(f)
                }
            }

            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(lifecycleCallbacks, true)
        }
    }

    private fun handleFragment(f: Fragment) {
        if (f is Injectable) {
            AndroidSupportInjection.inject(f)
        }
    }
}

