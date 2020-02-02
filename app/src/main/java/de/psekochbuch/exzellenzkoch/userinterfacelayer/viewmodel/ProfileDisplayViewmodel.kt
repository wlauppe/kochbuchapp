package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class ProfileDisplayViewmodel : ViewModel() {

    var repo = PublicRecipeFakeRepository()
    var recipes: LiveData<List<PublicRecipe>> = repo.getPublicRecipes()


    fun isOwner():Boolean{
        return true

    }
}