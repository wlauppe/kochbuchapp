package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

class IngredientAmount (

                        /**
                         * Name of the ingredient
                         */
                        var ingredient:String = "",

                        /**
                         * The quantity of the ingredient
                         */
                        var quantity:Double = 0.0,

                        /**
                         * The unit of the amount from the ingredient
                         */
                        var unit:Unit = Unit.Liter

){


    override fun toString(): String {

        return  ingredient.plus(quantity.toString()).plus(unit.toString())
    }

    // TODO parser Methode, um IngredientText aus DTO umzuwandeln
}
