package dev.suhockii.lifetest.di.module

import android.app.Application
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulers
import dev.suhockii.lifetest.util.rx_transformers.RxSchedulersImpl

@Module(includes = [AndroidInjectionModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideRxSchedulers(): RxSchedulers {
        return RxSchedulersImpl()
    }
}
