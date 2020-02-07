package de.psekochbuch.exzellenzkoch

import android.content.Context

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModelFactory
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewModelFactory
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel

object InjectorUtils {


     private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
        //return PublicRecipeFakeRepositoryImp.getInstance()
         return PublicRecipeRepositoryImp.getInstance()

    }


    //Beispiel für eine Viewmodel Factory

   fun provideRecipeListViewmodelFactory(
        context: Context
    ): RecipeListViewModelFactory {
        val repository = getPublicRecipeRepository(context)
        return RecipeListViewModelFactory(repository)
    }

    fun provideAdminViewModelFactory(
        context: Context
    ): AdminViewModelFactory {
        val recipeRepo = getPublicRecipeRepository(context)
        return AdminViewModelFactory(recipeRepo)
    }

}
