package dev.suhockii.lifetest.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.suhockii.lifetest.ui.main.MainActivity;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}

