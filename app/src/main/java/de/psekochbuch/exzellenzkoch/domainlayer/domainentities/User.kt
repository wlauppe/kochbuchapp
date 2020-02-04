package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.os.Parcel
import android.os.Parcelable

class User(id:String, img: String, desc :String) {

    var isAdmin = false

    var userID :String? = id

    var img : String? = img

    var description: String? = desc

}
