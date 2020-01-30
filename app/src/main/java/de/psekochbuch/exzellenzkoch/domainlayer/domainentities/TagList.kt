package com.example.simplekochbuchapp.domainlayer.domainentities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Holds a list of tags that are given by the app developers.
 * This class can be extended to give the user the ability to add personal tags.
 */
class TagList {


    fun getTags(): List<String> {

        var list:MutableList<String> = ArrayList()

        list.add("süß")
        list.add("herzhaft")

        return list
    }
}
