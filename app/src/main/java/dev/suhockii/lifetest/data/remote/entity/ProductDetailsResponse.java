package dev.suhockii.lifetest.data.remote.entity;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
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

    @Ignore
    public ProductDetailsResponse(String id) {
        this.id = id;
    }

    protected ProductDetailsResponse(Parcel in) {
        this.id = in.readString();
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.price = in.readInt();
        this.description = in.readString();
    }

    public static final Creator<ProductDetailsResponse> CREATOR = new Creator<ProductDetailsResponse>() {
        @Override
        public ProductDetailsResponse createFromParcel(Parcel source) {return new ProductDetailsResponse(source);}

        @Override
        public ProductDetailsResponse[] newArray(int size) {return new ProductDetailsResponse[size];}
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDetailsResponse that = (ProductDetailsResponse) o;

        if (price != that.price) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
