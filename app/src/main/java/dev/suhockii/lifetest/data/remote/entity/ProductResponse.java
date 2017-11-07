package dev.suhockii.lifetest.data.remote.entity;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import dev.suhockii.lifetest.model.Product;

public class ProductResponse implements Product {
    @SerializedName("product_id")
    private String id;

    @SerializedName("image")
    private String imageUrl;

    private String name;
    private int price;

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
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.imageUrl);
        dest.writeString(this.name);
        dest.writeInt(this.price);
    }

    public ProductResponse() {}

    protected ProductResponse(Parcel in) {
        this.id = in.readString();
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.price = in.readInt();
    }

    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel source) {return new ProductResponse(source);}

        @Override
        public ProductResponse[] newArray(int size) {return new ProductResponse[size];}
    };
}
