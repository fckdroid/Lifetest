package dev.suhockii.lifetest.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.suhockii.lifetest.di.scope.ActivityScope
import dev.suhockii.lifetest.ui.main.MainActivity

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}

