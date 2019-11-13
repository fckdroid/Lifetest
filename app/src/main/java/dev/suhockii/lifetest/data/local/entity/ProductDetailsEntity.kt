package dev.suhockii.lifetest.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import android.os.Parcel
import android.os.Parcelable

import dev.suhockii.lifetest.model.ProductDetails

@Entity(
    tableName = "ProductDetails",
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(entity = ProductDetailsEntity::class, parentColumns = ["id"], childColumns = ["id"])],
    indices = [Index("id")]
)
class ProductDetailsEntity : ProductDetails {

    override var id: String
    override var imageUrl: String = ""
    override var name: String = ""
    override var price: Int = 0
    override var description: String = ""

    constructor(
        id: String, imageUrl: String, name: String,
        price: Int, description: String
    ) {
        this.id = id
        this.imageUrl = imageUrl
        this.name = name
        this.price = price
        this.description = description
    }

    @Ignore
    constructor(id: String) {
        this.id = id
    }

    internal constructor(builder: Builder) {
        this.id = builder.id!!
        this.imageUrl = builder.imageUrl!!
        this.price = builder.price
        this.name = builder.name!!
        this.description = builder.description!!
    }

    class Builder {
        var id: String? = null
        var imageUrl: String? = null
        var name: String? = null
        var price: Int = 0
        var description: String? = null

        fun build(): ProductDetailsEntity {
            return ProductDetailsEntity(this)
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

        fun description(description: String): Builder {
            this.description = description
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
        dest.writeString(this.description)
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()!!
        this.imageUrl = `in`.readString()!!
        this.name = `in`.readString()!!
        this.price = `in`.readInt()
        this.description = `in`.readString()!!
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false

        val that = o as ProductDetailsEntity?

        if (price != that!!.price) return false
        if (id != that.id) return false
        if (if (imageUrl != null) imageUrl != that.imageUrl else true)
            return false
        if (if (name != null) name != that.name else that.name != null) return false
        return if (description != null) description == that.description else that.description == null
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + if (imageUrl != null) imageUrl!!.hashCode() else 0
        result = 31 * result + if (name != null) name!!.hashCode() else 0
        result = 31 * result + price
        result = 31 * result + if (description != null) description!!.hashCode() else 0
        return result
    }

    companion object {

        @JvmField
        val CREATOR: Parcelable.Creator<ProductDetailsEntity> = object : Parcelable.Creator<ProductDetailsEntity> {
            override fun createFromParcel(source: Parcel): ProductDetailsEntity {
                return ProductDetailsEntity(source)
            }

            override fun newArray(size: Int): Array<ProductDetailsEntity?> {
                return arrayOfNulls(size)
            }
        }
    }
}
