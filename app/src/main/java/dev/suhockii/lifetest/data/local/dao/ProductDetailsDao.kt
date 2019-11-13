package dev.suhockii.lifetest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import dev.suhockii.lifetest.data.local.entity.ProductDetailsEntity
import dev.suhockii.lifetest.data.local.entity.ProductEntity
import io.reactivex.Single

@Dao
interface ProductDetailsDao {

    /**
     * Returns details of some product from local database.
     *
     * @param id product identifier. Get it here [ProductEntity.getId]
     * @return ProductDetailsEntity object
     */
    @Query("SELECT * FROM ProductDetails WHERE id = :id")
    fun getProductDetails(id: String): Single<ProductDetailsEntity>

    /**
     * Save [ProductDetailsEntity] to local database.
     *
     * @param productDetailsEntity what we would like to insert in ProductDetails table.
     */
    @Insert
    fun saveProductDetails(productDetailsEntity: ProductDetailsEntity)
}
