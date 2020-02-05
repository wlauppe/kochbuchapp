package de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class PublicRecipeFakeRepository : PublicRecipeRepository {
    override suspend fun removePublicRecipe(recipe: PublicRecipe) {
    }
    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {

        val recipe1 = PublicRecipe(listOf(),"trockener Sandkuchen", 5.0, "Backe backe Kuchen", listOf("trocken","kuchen","ungeniessbar"),5, 2,null, 5);
        recipe1.image = "https://i.ytimg.com/vi/uZfco9h0C_s/hqdefault.jpg"
        //val recipe2 = PublicRecipe(listOf(), "ganz trockener Sandkuchen", 2.0, "Backe backe Kuchen",tags=listOf("trocken","kuchen","ungeniessbar"))
        val recipe2= recipe1
        val recipe3 = PublicRecipe(listOf(),"trockener Sandkuchen", 5.0, "Backe backe Kuchen", listOf("trocken","kuchen","ungeniessbar"),5, 2,null, 5);
        val recipe4 = PublicRecipe(listOf(),"trockener Sandkuchen", 5.0, "Backe backe Kuchen", listOf("trocken","kuchen","ungeniessbar"),5, 2,null, 5);
        //setID
        recipe1.id = 4
        recipe2.id = 2
        recipe3.id = 3
        recipe4.id = 5
        val list = listOf(recipe1,recipe2, recipe3, recipe4)
        val ld: MutableLiveData<List<PublicRecipe>> = MutableLiveData(list)
        return ld
    }

    fun getUsers():MutableLiveData<List<User>>{
        val user1 = User("Jürgern", "bild", "Toastbrot")
        val user2 = User("Bürgern", "bild", "Toast")
        val user3 = User("Lürgern", "bild", "Moin")

        val list = listOf<User>(user1, user2, user3)
        val ld : MutableLiveData<List<User>> = MutableLiveData(list)
        return ld
    }

    // test for the user fragments
    fun getUser() :User {
        return User("Bernd", "bild", "Ich mag Brot")
    }
}