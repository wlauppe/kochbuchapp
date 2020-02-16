package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * The IngredientAmount class bundles inforamtion about the ingredient name, the quantity and which unit it contains.
 * @param ingredient: The name of the particular ingredient stored in a String
 * @param quantity: The amount of the specific ingredient.
 * @param unit: The unit in which referres to the quantity.
 */
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
