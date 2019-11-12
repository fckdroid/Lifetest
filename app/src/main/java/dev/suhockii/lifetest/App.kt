package dev.suhockii.lifetest

import android.app.Application

import com.crashlytics.android.Crashlytics

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dev.suhockii.lifetest.di.AppInjector
import io.fabric.sdk.android.Fabric

open class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
