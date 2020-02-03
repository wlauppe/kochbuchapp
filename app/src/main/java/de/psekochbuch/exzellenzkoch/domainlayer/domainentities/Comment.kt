package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.sql.Timestamp

class Comment ( val text : String,
                val publicationTime : Timestamp ) {

     private var id: Int? = null

}