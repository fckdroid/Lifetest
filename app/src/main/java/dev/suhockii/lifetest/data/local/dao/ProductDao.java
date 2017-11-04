package dev.suhockii.lifetest.data.local.dao;

import android.arch.persistence.room.Dao;
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
}
