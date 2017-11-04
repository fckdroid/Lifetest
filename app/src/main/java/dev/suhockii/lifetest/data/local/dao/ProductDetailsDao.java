package dev.suhockii.lifetest.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity;
import dev.suhockii.lifetest.data.local.entity.ProductEntity;
import io.reactivex.Single;

@Dao
public interface ProductDetailsDao {
    /**
     * Returns details of some product from local database.
     *
     * @param id product identifier. Get it here {@link ProductEntity#getId()}
     * @return ProductDetailsEntity object
     */
    @Query("SELECT * FROM ProductDetails WHERE id = :id")
    Single<ProductDetailsEntity> getProductDetails(long id);
}
