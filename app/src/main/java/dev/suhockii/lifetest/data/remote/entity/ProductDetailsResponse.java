package dev.suhockii.lifetest.data.remote.entity;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import dev.suhockii.lifetest.model.ProductDetails;

public class ProductDetailsResponse implements ProductDetails {

    @SerializedName("product_id")
    private String id;

    @SerializedName("image")
    private String imageUrl;

    private String name;
    private int price;
    private String description;

    @NonNull
    @Override
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
}
