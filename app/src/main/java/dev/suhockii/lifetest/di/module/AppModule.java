package dev.suhockii.lifetest.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dev.suhockii.lifetest.util.RxSchedulers;
import dev.suhockii.lifetest.util.RxSchedulersImpl;

@Module(includes = AndroidInjectionModule.class)
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public RxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }
}
