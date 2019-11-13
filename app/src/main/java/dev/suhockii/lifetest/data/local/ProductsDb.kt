package dev.suhockii.lifetest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

import dev.suhockii.lifetest.data.local.dao.ProductDao
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity
import dev.suhockii.lifetest.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class, ProductDetailsEntity::class], version = 1, exportSchema = true)
abstract class ProductsDb : RoomDatabase() {
    /**
     * Provides access to ProductDao
     */
    abstract val productDao: ProductDao

    /**
     * Provides access to ProductDetailsDao
     */
    abstract val productDetailsDao: ProductDetailsDao
}
