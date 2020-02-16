package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

/**
 * The Comment class contains the bundle which represents a comment for a public recipe. It contains information
 * about the author, the creationdate of the comment, an unique identifyer and the text which will be displayed.
 * @param commentId: An unique identifyer through wich the comment can be accessed
 * @param user: The author of the comment. The author can edit, or remove the existing comment.
 * @param text: The text of the comment, which will be displayed
 * @param publicationTime: The Date, of the last change when the comment was created, or edited
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