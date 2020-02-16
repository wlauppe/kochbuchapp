package de.psekochbuch.exzellenzkoch.domainlayer.domainentities


class User(
    /**
     * unique identifier of the User on the server
     */
    var userId:String,
    /**
     * the url of the picture on the server of the recipe
     */
    var imgUrl: String = "",
    /**
     * the description of the user profile
     */
    var description :String = "")