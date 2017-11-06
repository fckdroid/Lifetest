package dev.suhockii.lifetest.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dev.suhockii.lifetest.App;
import dev.suhockii.lifetest.di.module.AppModule;
import dev.suhockii.lifetest.di.module.LocalDataModule;
import dev.suhockii.lifetest.di.module.ActivityModule;
import dev.suhockii.lifetest.di.module.RemoteDataModule;
import dev.suhockii.lifetest.di.module.RepositoryModule;
import dev.suhockii.lifetest.ui.products.ProductsPresenter;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AppModule.class,
                ActivityModule.class,
                LocalDataModule.class,
                RemoteDataModule.class,
                RepositoryModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    ProductsPresenter getProductsPresenter();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
