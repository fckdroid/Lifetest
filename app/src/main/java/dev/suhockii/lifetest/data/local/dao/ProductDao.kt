package dev.suhockii.lifetest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import dev.suhockii.lifetest.data.local.entity.ProductEntity
import io.reactivex.Single

@Dao
interface ProductDao {

    /**
     * @return list of [ProductEntity] from local database.
     */
    @get:Query("SELECT * FROM Products")
    val products: Single<List<ProductEntity>>

    /**
     * Save products [ProductEntity] to local database.
     *
     * @param productEntities what we would like to insert in Products table.
     */
    @Insert
    fun saveProducts(productEntities: List<ProductEntity>)
}
