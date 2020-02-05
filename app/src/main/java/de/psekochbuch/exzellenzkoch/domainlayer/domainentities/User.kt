package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.os.Parcel
import android.os.Parcelable

class User(val userId:String, var img: String = "", var desc :String = "") {

    var isAdmin = false


}
