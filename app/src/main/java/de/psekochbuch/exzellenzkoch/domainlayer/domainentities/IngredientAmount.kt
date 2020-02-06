package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

class IngredientAmount (val ingredient:String = "",
                        val quantity:Double = 0.0,
                        val unit: Unit   )

//Todo Unit kann man noch nicht mit Konstruktor aufrufen.


    override fun toString(): String {

        return  ingredient.plus(quantity.toString()).plus(unit.toString())
    }

    // TODO parser Methode, um IngredientText aus DTO umzuwandeln
}
