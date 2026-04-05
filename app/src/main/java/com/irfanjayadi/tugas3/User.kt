package com.irfanjayadi.tugas3

import android.os.Parcel
import android.os.Parcelable

data class User(
    val nama: String,
    val nim: String,
    val prodi: String,
    val gender: String,
    val hobi: String,
    val alasanJoining: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeString(nim)
        parcel.writeString(prodi)
        parcel.writeString(gender)
        parcel.writeString(hobi)
        parcel.writeString(alasanJoining) // Tulis alasanJoining
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}