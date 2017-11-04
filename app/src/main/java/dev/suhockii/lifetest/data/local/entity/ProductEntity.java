package dev.suhockii.lifetest.data.local.entity;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

import dev.suhockii.lifetest.model.Product;

@Entity(
        tableName = "Products",
        primaryKeys = "id"
)
public class ProductEntity implements Product {
    private long id;
    private String imageUrl;
    private String name;
    private int price;

    @Override
    public long getId() {
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

    public void setId(long id) {
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
}
