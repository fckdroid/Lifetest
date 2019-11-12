package dev.suhockii.lifetest.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.suhockii.lifetest.data.local.dao.ProductDao;
import dev.suhockii.lifetest.data.local.dao.ProductDetailsDao;
import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity;
import dev.suhockii.lifetest.data.local.entity.ProductEntity;

@Database(entities = {ProductEntity.class, ProductDetailsEntity.class},
        version = 1, exportSchema = true)
public abstract class ProductsDb extends RoomDatabase {
    abstract public ProductDao getProductDao();
    abstract public ProductDetailsDao getProductDetailsDao();
}
