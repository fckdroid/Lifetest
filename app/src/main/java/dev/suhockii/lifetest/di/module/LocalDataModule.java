package dev.suhockii.lifetest.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.suhockii.lifetest.data.local.ProductsDb;
import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.data.remote.ProductsApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class LocalDataModule {
    @Provides
    @Singleton
    ProductsDb provideProductsDb(Context appContext) {
        return Room.databaseBuilder(appContext, ProductsDb.class, "products.db").build();
    }

    @Singleton
    @Provides
    ProductDao provideProductDao(ProductsDb db) {
        return db.getProductDao();
    }

    @Singleton
    @Provides
    ProductDetailsDao provideProductDetailsDao(ProductsDb db) {
        return db.getProductDetailsDao();
    }
}
