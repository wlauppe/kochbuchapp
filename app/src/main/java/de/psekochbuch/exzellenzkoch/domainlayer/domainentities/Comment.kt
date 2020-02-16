package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

/**
 * The Comment class represents a Comment, which can be displayed within a PublicRecipe.
 * @param commentId: An unique identifyer for the specific comment
 * @param user: The author of the comment directly connected with it
 * @param text: The content of the Comment, which will be displayed
 * @param publicationTime: The Date of the publication of the comment
 */
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