package de.psekochbuch.exzellenzkoch

import android.content.Context

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewModelFactory
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel

object InjectorUtils {


     private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
        return PublicRecipeFakeRepositoryImp.getInstance()

    }


    //Beispiel für eine Viewmodel Factory

   fun provideRecipeListViewmodelFactory(
        context: Context
    ): RecipeListViewModelFactory {
        val repository = getPublicRecipeRepository(context)
        return RecipeListViewModelFactory(repository)
    }

}
