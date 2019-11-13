package dev.suhockii.lifetest.data.remote.entity

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import dev.suhockii.lifetest.model.Product

class ProductResponse : Product {
    @SerializedName("product_id")
    override var id: String = ""

    @SerializedName("image")
    override var imageUrl: String = ""

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
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()!!
        this.imageUrl = `in`.readString()!!
        this.name = `in`.readString()!!
        this.price = `in`.readInt()
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<ProductResponse> = object : Parcelable.Creator<ProductResponse> {
            override fun createFromParcel(source: Parcel): ProductResponse {
                return ProductResponse(source)
            }

            override fun newArray(size: Int): Array<ProductResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}
