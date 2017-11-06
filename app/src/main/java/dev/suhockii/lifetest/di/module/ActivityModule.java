package dev.suhockii.lifetest.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.suhockii.lifetest.di.scope.ActivityScope;
import dev.suhockii.lifetest.ui.main.MainActivity;

@ActivityScope
@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}

