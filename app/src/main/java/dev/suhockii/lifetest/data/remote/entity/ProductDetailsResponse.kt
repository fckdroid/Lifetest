package dev.suhockii.lifetest.data.remote.entity

import androidx.room.Ignore
import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import dev.suhockii.lifetest.model.ProductDetails

class ProductDetailsResponse : ProductDetails {

    @SerializedName("product_id")
    override var id: String = ""

    @SerializedName("image")
    override var imageUrl: String = ""

    @SerializedName(value = "description", alternate = ["decription", "user"])
    override var description: String? = null

    override var name: String = ""
    override var price: Int = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.imageUrl)
        dest.writeString(this.name)
        dest.writeInt(this.price)
        dest.writeString(this.description)
    }

    @Ignore
    constructor(id: String) {
        this.id = id
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()!!
        this.imageUrl = `in`.readString()!!
        this.name = `in`.readString()!!
        this.price = `in`.readInt()
        this.description = `in`.readString()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as ProductDetailsResponse?

        if (price != that!!.price) return false
        if (if (id != null) id != that.id else that.id != null) return false
        if (if (imageUrl != null) imageUrl != that.imageUrl else that.imageUrl != null)
            return false
        if (if (name != null) name != that.name else that.name != null) return false
        return if (description != null) description == that.description else that.description == null
    }

    override fun hashCode(): Int {
        var result = if (id != null) id!!.hashCode() else 0
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + price
        result = 31 * result + if (description != null) description!!.hashCode() else 0
        return result
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<ProductDetailsResponse> = object : Parcelable.Creator<ProductDetailsResponse> {
            override fun createFromParcel(source: Parcel): ProductDetailsResponse {
                return ProductDetailsResponse(source)
            }

            override fun newArray(size: Int): Array<ProductDetailsResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}
