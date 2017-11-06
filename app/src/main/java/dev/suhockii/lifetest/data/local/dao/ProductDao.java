package dev.suhockii.lifetest.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import dev.suhockii.lifetest.data.local.entity.ProductEntity;
import io.reactivex.Single;

@Dao
public interface ProductDao {

    /**
     * @return list of {@link ProductEntity} from local database.
     */
    @Query("SELECT * FROM Products")
    Single<List<ProductEntity>> getProducts();

    /**
     * Save products {@link ProductEntity} to local database.
     *
     * @param productEntities what we would like to insert in Products table.
     */
    @Insert
    void saveProducts(List<ProductEntity> productEntities);
}
