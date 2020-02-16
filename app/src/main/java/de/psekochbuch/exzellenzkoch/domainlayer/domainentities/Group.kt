package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * The Group class contains all the representing ifnroatmion
 * @param creator: The user who creates the group. The creator can add, or remove other users
 * @param name: The Groupname which will be displayed publicly
 */
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