package dev.suhockii.lifetest.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import dev.suhockii.lifetest.model.ProductDetails;

@Entity(tableName = "ProductDetails",
        primaryKeys = "id",
        foreignKeys = {
                @ForeignKey(entity = ProductEntity.class,
                        parentColumns = "id",
                        childColumns = "id")
        },
        indices = @Index(value = "id"))
public class ProductDetailsEntity implements ProductDetails {

    @NonNull
    private String id;
    private String imageUrl;
    private String name;
    private int price;
    private String description;

    public ProductDetailsEntity(@NonNull String id, String imageUrl, String name,
                                int price, String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    @NonNull
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
