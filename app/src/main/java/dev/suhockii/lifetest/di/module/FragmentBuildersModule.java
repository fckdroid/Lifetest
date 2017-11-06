package dev.suhockii.lifetest.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dev.suhockii.lifetest.ui.details.DetailsFragment;
import dev.suhockii.lifetest.ui.products.ProductsFragment;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = ProductsFragmentModule.class)
    abstract ProductsFragment contributeProductsFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment contributeDetailsFragment();
}
