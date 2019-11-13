package dev.suhockii.lifetest.model

import android.os.Parcelable

interface Product : Parcelable {

    val id: String
    val name: String
    val price: Int
    val imageUrl: String
}
