package dev.suhockii.lifetest.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.suhockii.lifetest.ui.details.DetailsFragment;
import dev.suhockii.lifetest.ui.list.ListFragment;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract DetailsFragment contributeDetailsFragment();

    @ContributesAndroidInjector
    abstract ListFragment contributeListFragment();
}
