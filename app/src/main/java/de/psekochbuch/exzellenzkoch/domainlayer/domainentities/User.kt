package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.os.Parcel
import android.os.Parcelable

class User() : Parcelable{

    var isAdmin = false

    var userID :String? = null

    var img : String? = null

    var description: String? = null

    constructor(parcel: Parcel) : this() {
        isAdmin = parcel.readByte() != 0.toByte()
        userID = parcel.readString()
        img = parcel.readString()
        description = parcel.readString()
    }



    //PArceble for safeargs
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isAdmin) 1 else 0)
        parcel.writeString(userID)
        parcel.writeString(img)
        parcel.writeString(description)
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
