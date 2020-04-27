package com.example.unsplashphotos.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(

    @SerializedName("id")
    var id: String,

    @SerializedName("urls")
    val urls: Urls,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("alt_description")
    val altDescription: String,


    @SerializedName("likes")
    val likes: Int


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readParcelable(Urls::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    data class Urls(
        @SerializedName("full")
        val full: String,

        @SerializedName("raw")
        val raw: String,

        @SerializedName("regular")
        val regular: String,

        @SerializedName("small")
        val small: String,

        @SerializedName("thumb")
        val thumb: String
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(full)
            parcel.writeString(raw)
            parcel.writeString(regular)
            parcel.writeString(small)
            parcel.writeString(thumb)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Urls> {
            override fun createFromParcel(parcel: Parcel): Urls {
                return Urls(parcel)
            }

            override fun newArray(size: Int): Array<Urls?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(urls, flags)
        parcel.writeString(createdAt)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(description)
        parcel.writeString(altDescription)
        parcel.writeInt(likes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnsplashPhoto> {
        override fun createFromParcel(parcel: Parcel): UnsplashPhoto {
            return UnsplashPhoto(parcel)
        }

        override fun newArray(size: Int): Array<UnsplashPhoto?> {
            return arrayOfNulls(size)
        }
    }

}

