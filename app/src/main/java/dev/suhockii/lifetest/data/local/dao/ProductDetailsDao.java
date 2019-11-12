package dev.suhockii.lifetest.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
    Single<ProductDetailsEntity> getProductDetails(String id);

    /**
     * Save {@link ProductDetailsEntity} to local database.
     *
     * @param productDetailsEntity what we would like to insert in ProductDetails table.
     */
    @Insert
    void saveProductDetails(ProductDetailsEntity productDetailsEntity);
}
