package de.psekochbuch.exzellenzkoch.datalayer.remote.dto

/**
 * Data Transfer Object of the ingredients
 */
data class IngredientDto(
        /**
         * Id of the chapter of the ingredient
         */
        var chapterId:Int,

        /**
         * Name of the ingredient
         */
        var nameIngredient:String,

        /**
         * Amount of the ingredient
         */
        var amount:Double,

        /**
         * Unit of the ingredient
         */
        var unit:String
)