package dev.suhockii.lifetest.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import android.os.Parcel
import android.os.Parcelable

import dev.suhockii.lifetest.model.Product

@Entity(tableName = "Products", primaryKeys = ["id"])
class ProductEntity : Product {
    override var id: String
    override var imageUrl: String = ""
    override var name: String = ""
    override var price: Int = 0

    constructor(id: String, imageUrl: String, name: String, price: Int) {
        this.id = id
        this.imageUrl = imageUrl
        this.name = name
        this.price = price
    }

    @Ignore
    constructor(id: String) {
        this.id = id
    }


    internal constructor(builder: Builder) {
        this.id = builder.id
        this.imageUrl = builder.imageUrl
        this.price = builder.price
        this.name = builder.name
    }

    class Builder {
        var id: String = ""
        var imageUrl: String = ""
        var name: String = ""
        var price: Int = 0

        fun build(): ProductEntity {
            return ProductEntity(this)
        }

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun imageUrl(imageUrl: String): Builder {
            this.imageUrl = imageUrl
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun price(price: Int): Builder {
            this.price = price
            return this
        }
    }


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
        val CREATOR: Parcelable.Creator<ProductEntity> = object : Parcelable.Creator<ProductEntity> {
            override fun createFromParcel(source: Parcel): ProductEntity {
                return ProductEntity(source)
            }

            override fun newArray(size: Int): Array<ProductEntity?> {
                return arrayOfNulls(size)
            }
        }
    }
}
