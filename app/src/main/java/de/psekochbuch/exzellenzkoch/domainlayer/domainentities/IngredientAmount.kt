package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

class IngredientAmount constructor (ingredient:String, quantity:Double, unit: Unit){ //TODO unit

    private var ingredient:String? = ingredient
    private var quantity:Double? = quantity
    private var unit: Unit =  unit

    // TODO parser Methode, um IngredientText aus DTO umzuwandeln
}