package dev.suhockii.lifetest.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dev.suhockii.lifetest.App;
import dev.suhockii.lifetest.di.module.LocalDataModule;
import dev.suhockii.lifetest.di.module.MainActivityModule;
import dev.suhockii.lifetest.di.module.RemoteDataModule;
import dev.suhockii.lifetest.di.module.RepoModule;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                MainActivityModule.class,
                LocalDataModule.class,
                RemoteDataModule.class,
                RepoModule.class
        }
)
public interface AppComponent {
    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
