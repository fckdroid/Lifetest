package dev.suhockii.lifetest.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.suhockii.lifetest.ui.details.DetailsFragment
import dev.suhockii.lifetest.ui.products.ProductsFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeProductsFragment(): ProductsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailsFragment(): DetailsFragment
}
