package de.psekochbuch.exzellenzkoch.domainlayer.domainentities


class Group (creator: User, name:String){
    fun getUsers():List<User>{
        return emptyList() //TODO
    }

    fun getGroupName():String?{
        return "" //TODO

    }
    fun getCreator(): User?{
        return null //TODO
    }

    fun getRecipes():List<Recipe>{
        return emptyList() //TODO
    }

}