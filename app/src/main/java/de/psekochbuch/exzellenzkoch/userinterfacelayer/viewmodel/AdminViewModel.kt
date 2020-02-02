package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class AdminViewModel : ViewModel() {


    var recipes: LiveData<List<PublicRecipe>>? = null

    var profiles: LiveData<List<User>>? = null


    fun deleteRecipe(recipeID: Int){

    }

    fun deleteUser(userID: String){

    }
}
