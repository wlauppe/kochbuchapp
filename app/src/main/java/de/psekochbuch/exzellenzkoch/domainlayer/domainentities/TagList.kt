package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * Holds a list of tags that are given by the app developers.
 * This class can be extended to give the user the ability to add personal tags.
 */
class TagList(val tags:List<String>) {

    //auskommentiert, da die Methode die gleiche Signatur hätte wie das atttribut tags
   // fun getTags(): List<String> {
    //    var list:MutableList<String> = ArrayList()
    //    list.add("süß")
    //    list.add("herzhaft")
   //     return list
    //}
}
