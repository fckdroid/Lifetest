package dev.suhockii.lifetest.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.os.Parcel;
import android.support.annotation.NonNull;

import dev.suhockii.lifetest.model.ProductDetails;

@Entity(tableName = "ProductDetails",
        primaryKeys = "id",
        foreignKeys = {
                @ForeignKey(entity = ProductDetailsEntity.class,
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

    @Ignore
    public ProductDetailsEntity(@NonNull String id) {
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

    ProductDetailsEntity(@NonNull ProductDetailsEntity.Builder builder) {
        this.id = builder.id;
        this.imageUrl = builder.imageUrl;
        this.price = builder.price;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class Builder {
        private String id;
        private String imageUrl;
        private String name;
        private int price;
        private String description;

        public ProductDetailsEntity build() {
            return new ProductDetailsEntity(this);
        }

        public ProductDetailsEntity.Builder id(String id) {
            this.id = id;
            return this;
        }

        public ProductDetailsEntity.Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public ProductDetailsEntity.Builder name(String name) {
            this.name = name;
            return this;
        }

        public ProductDetailsEntity.Builder price(int price) {
            this.price = price;
            return this;
        }

        public ProductDetailsEntity.Builder description(String description) {
            this.description = description;
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
        dest.writeString(this.description);
    }

    protected ProductDetailsEntity(Parcel in) {
        this.id = in.readString();
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.price = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<ProductDetailsEntity> CREATOR = new Creator<ProductDetailsEntity>() {
        @Override
        public ProductDetailsEntity createFromParcel(Parcel source) {return new ProductDetailsEntity(source);}

        @Override
        public ProductDetailsEntity[] newArray(int size) {return new ProductDetailsEntity[size];}
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDetailsEntity that = (ProductDetailsEntity) o;

        if (price != that.price) return false;
        if (!id.equals(that.id)) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
