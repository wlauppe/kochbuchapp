package de.psekochbuch.exzellenzkoch.domainlayer

import android.media.Image
import androidx.room.Entity
import de.psekochbuch.exzellenzkoch.domainlayer.Group

class User {

    //Attributes

    var group: Group? = null

    var isAdmin:Boolean? = null

    var image:Image? = null

    var description:String? = null

    //Functions

    fun isAdmin():Boolean {
        return this.isAdmin()
    }

}