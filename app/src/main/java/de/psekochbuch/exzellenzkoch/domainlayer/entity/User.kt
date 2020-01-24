package de.psekochbuch.exzellenzkoch.domainlayer.entity

import android.media.Image

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