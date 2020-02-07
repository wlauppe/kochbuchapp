package de.psekochbuch.exzellenzkoch

import android.content.Context
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositories.IngredientAmountRepository
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.TagFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.*


object InjectorUtils {

    private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
        return PublicRecipeFakeRepositoryImp.getInstance()
        //return PublicRecipeRepositoryImp.getInstance()
    }

    private fun getUserRepository(context: Context): UserRepository {
        return UserFakeRepositoryImp.getInstance()
    }

    private fun getIngredientAmountRepository(context: Context): IngredientAmountRepository? {
        return null //IngredientAmountRepository()
        // TODO Instance return, dazu companion obj in IngAmRepo und dann not null
    }

    private fun getPrivateRecipeRepository(context: Context): PrivateRecipeRepository {
        // TODO Testweise Fakerepo benutzt
        return PrivateRecipeFakeRepositoryImp.getInstance()
    }

    private fun getEditTagRepository(context: Context): TagRepository {
        // TODO Testweise Fakerepo benutzt
        return TagFakeRepositoryImp.getInstance()
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

    fun provideCreateRecipeViewModelFactory(context: Context): CreateRecipeViewModelFactory {
        val repository = getPrivateRecipeRepository(context)
        return CreateRecipeViewModelFactory(repository)
    }

    //For Feed VM Factory
    fun provideFeedViewModelFactory(context: Context): FeedViewModelFactory {
        val repository = getPublicRecipeRepository(context)
        return FeedViewModelFactory(repository)
    }

    fun provideEditTagViewModelFactory(context: Context): EditTagViewModelFactory {
        val repository = getEditTagRepository(context)
        return EditTagViewModelFactory(repository)
    }

    fun provideAdminViewModelFactory(context: Context): AdminViewModelFactory {
        val recipeRepo = getPublicRecipeRepository(context)
        return AdminViewModelFactory(recipeRepo)
    }

    fun provideProfileDisplaViewModelFactory(context: Context): ProfileDisplayViewModelFactory {
        val repository = getUserRepository(context)
        return ProfileDisplayViewModelFactory(repository)
    }
}
