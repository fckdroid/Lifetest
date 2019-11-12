package dev.suhockii.lifetest.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import android.os.Parcel;
import androidx.annotation.NonNull;

import dev.suhockii.lifetest.model.Product;

@Entity(
        tableName = "Products",
        primaryKeys = "id"
)
public class ProductEntity implements Product {
    @NonNull
    private String id;
    private String imageUrl;
    private String name;
    private int price;

    public ProductEntity(@NonNull String id, String imageUrl, String name, int price) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
    }

    @Ignore
    public ProductEntity(@NonNull String id) {
        this.id = id;
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


    ProductEntity(@NonNull Builder builder) {
        this.id = builder.id;
        this.imageUrl = builder.imageUrl;
        this.price = builder.price;
        this.name = builder.name;
    }

    public static class Builder {
        private String id;
        private String imageUrl;
        private String name;
        private int price;

        public ProductEntity build() {
            return new ProductEntity(this);
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }
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

    protected ProductEntity(Parcel in) {
        this.id = in.readString();
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.price = in.readInt();
    }

    public static final Creator<ProductEntity> CREATOR = new Creator<ProductEntity>() {
        @Override
        public ProductEntity createFromParcel(Parcel source) {return new ProductEntity(source);}

        @Override
        public ProductEntity[] newArray(int size) {return new ProductEntity[size];}
    };
}
