package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

class Comment (

    /**
     * Id of the comment
     */
    val commentId:Int,

    /**
     * The user which creates the comment
     */
    val user: String,

    /**
     * The comment text
     */
    var text:String,

    /**
     * The date of the comment, when it was created
     */
    val publicationTime: Date
)
{
}