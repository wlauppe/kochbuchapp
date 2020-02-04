package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class AdminViewModel : ViewModel() {

    var mrepoRecipe = PublicRecipeFakeRepository()

    var recipes:MutableLiveData<List<String>> = mrepoRecipe.getPublicRecipesAsStrings()

    var profiles: LiveData<List<User>>? = null

    var first  = "Piroggen"
    var second = "Pommes"
    var third = "Käse"
    var fourth = "Döner"
    var list = listOf<String>(first,second,third,fourth)

    val items: MutableLiveData<List<String>> = MutableLiveData(list)





    fun deleteRecipe(recipeID: Int) {

    }

    fun deleteUser(userID: String) {

    }
}
