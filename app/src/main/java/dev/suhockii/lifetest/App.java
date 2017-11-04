package dev.suhockii.lifetest;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

import dev.suhockii.lifetest.di.component.AppComponent;
import dev.suhockii.lifetest.di.component.DaggerAppComponent;
import dev.suhockii.lifetest.di.module.AppModule;
import dev.suhockii.lifetest.di.module.LocalDataModule;
import dev.suhockii.lifetest.di.module.RemoteDataModule;

public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .localDataModule(new LocalDataModule())
                .remoteDataModule(new RemoteDataModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
