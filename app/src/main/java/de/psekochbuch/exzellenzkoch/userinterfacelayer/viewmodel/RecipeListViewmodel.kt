package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class RecipeListViewmodel(repository: PublicRecipeRepository) : ViewModel() {

    var repo = repository



  // Alte Variante  val repo : PublicRecipeRepository = PublicRecipeFakeRepositoryImp()
  //Neue Variante jetzt wird es injected und ist als parameter verfügbar.

    var recipes : LiveData<List<PublicRecipe>> = repository.getPublicRecipes()



    fun deleteRecipe(id: Int?) {
       // repo.deleteRecipe(id!!)


    }


}