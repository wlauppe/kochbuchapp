package de.psekochbuch.exzellenzkoch.datalayer.remote.dto


data class UserDto(
    val userId :String,

    val email:String,

    val description:String,

    val markAsEvil : Boolean
)