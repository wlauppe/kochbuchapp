package de.psekochbuch.exzellenzkoch

import android.content.Context
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositories.IngredientAmountRepository

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModelFactory
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.ChangePasswordViewModelFactory
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.CreateRecipeViewModelFactory
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.RecipeListViewModelFactory


object InjectorUtils {


         private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
             //return PublicRecipeFakeRepositoryImp.getInstance()
             return PublicRecipeRepositoryImp.getInstance()
         }


         private fun getUserRepository(context: Context): UserRepository {
             return UserFakeRepositoryImp.getInstance()
         }

         private fun getIngretientAmountRepository(context: Context): IngredientAmountRepository? {
             return null //IngredientAmountRepository()
             // TODO Instance return, dazu companion obj in IngAmRepo und dann not null
         }

         private fun getPrivateRecipeRepository(context: Context): PrivateRecipeRepository? {
             return null // eigentlich: PrivateRecipeRepository.getInstance() aber gibts noch nicht
         }

         /*
     * Weitere Repos hier einfügen:
     * LOCAL: IngredientChapterRepository, PublicRecipeRepository, ShoppingListRepository
     * REMOTE: alle
     */


         //Beispiel für eine Viewmodel Factory

         fun provideRecipeListViewmodelFactory(context: Context): RecipeListViewModelFactory {
             val repository = getPublicRecipeRepository(context)
             return RecipeListViewModelFactory(repository)
         }

         fun provideChangePasswordViewModelFactory(context: Context): ChangePasswordViewModelFactory? {
             return null // TODO return Application
         }

         fun provideCreateRecipeViewModelFactory(context: Context): CreateRecipeViewModelFactory? {
             val repository = getPrivateRecipeRepository(context)
             return null // until CreateRecipeViewModelFactory(repository) works
         }

         fun provideAdminViewModelFactory(
             context: Context
         ): AdminViewModelFactory {
             val recipeRepo = getPublicRecipeRepository(context)
             return AdminViewModelFactory(recipeRepo)
         }

}
