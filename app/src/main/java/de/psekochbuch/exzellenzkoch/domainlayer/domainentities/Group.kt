package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * The Group class contains information about a specific group.
 * @param creator: The creator of the group. The creator can add or remove group members, or change the group name.
 * @param name: The name of the group which will be publicly displayed.
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