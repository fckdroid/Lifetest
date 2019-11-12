package dev.suhockii.lifetest.di.module;

import androidx.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.suhockii.lifetest.data.local.ProductsDb;
import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;

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
