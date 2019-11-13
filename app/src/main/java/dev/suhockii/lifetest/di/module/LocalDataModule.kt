package dev.suhockii.lifetest.di.module

import androidx.room.Room
import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dev.suhockii.lifetest.data.local.ProductsDb
import dev.suhockii.lifetest.data.local.dao.ProductDao
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao

@Module
class LocalDataModule {
    @Provides
    @Singleton
    fun provideProductsDb(appContext: Context): ProductsDb {
        return Room.databaseBuilder(appContext, ProductsDb::class.java, "products.db").build()
    }

    @Singleton
    @Provides
    fun provideProductDao(db: ProductsDb): ProductDao {
        return db.productDao
    }

    @Singleton
    @Provides
    fun provideProductDetailsDao(db: ProductsDb): ProductDetailsDao {
        return db.productDetailsDao
    }
}
