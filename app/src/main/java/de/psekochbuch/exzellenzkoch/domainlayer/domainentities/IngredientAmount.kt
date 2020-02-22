package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * The IngredientAmount class has the purpose to bundle information about the name, the quantity and the specific unit
 * of the entered Ingredient.
 * @param ingredient: The name of the ingredient
 * @param quantity: The amount of the ingredient as a Double precision digit
 * @param unit: The specific unit of the ingredient
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
                        var unit:String = ""

){


    override fun toString(): String {
        return  ingredient.plus(quantity.toString()).plus(unit.toString())
    }

    // TODO parser Methode, um IngredientText aus DTO umzuwandeln
}
