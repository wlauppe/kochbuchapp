package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

class IngredientAmount constructor (ingredient:String, quantity:Double, unit: String){ //TODO unit

    public var ingredient:String? = ingredient
    public var quantity:Double? = quantity
    public var unit: String =  unit

    // TODO parser Methode, um IngredientText aus DTO umzuwandeln
}