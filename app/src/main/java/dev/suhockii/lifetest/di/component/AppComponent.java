package dev.suhockii.lifetest.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dev.suhockii.lifetest.di.module.AppModule;
import dev.suhockii.lifetest.di.module.LocalDataModule;
import dev.suhockii.lifetest.di.module.RemoteDataModule;
import dev.suhockii.lifetest.di.module.RepoModule;

@Singleton
@Component(
        modules = {
                AppModule.class, LocalDataModule.class,
                RemoteDataModule.class, RepoModule.class
        }
)
public interface AppComponent {
}
