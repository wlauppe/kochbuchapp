package de.psekochbuch.exzellenzkoch

import android.content.Context
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.TagFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.TagRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationFakeImpl
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.LoginViewModelFactory
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.*


object InjectorUtils {

    private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
        //return PublicRecipeRepositoryImp.getInstance()
        return PublicRecipeFakeRepositoryImp.getInstance()
    }

    private fun getUserRepository(context: Context): UserRepository {
        //return UserFakeRepositoryImp.getInstance()
       return UserRepositoryImp.getInstance()
    }


    private fun getPrivateRecipeRepository(context: Context): PrivateRecipeRepository {
        return PrivateRecipeFakeRepositoryImp.getInstance()
        //return PrivateRecipeRepositoryImp.getInstance()
    }

    private fun getEditTagRepository(context: Context): TagRepository {
        return TagFakeRepositoryImp()

    }

    // For Login, Register and ChangePassword
    private fun getAuthentification(context: Context): Authentification {
        return AuthentificationFakeImpl()
    }

    /*
     * Weitere Repos hier einfügen:
     * LOCAL: IngredientChapterRepository, PublicRecipeRepository, ShoppingListRepository
     * REMOTE: alle
     */

    //Beispiel für eine Viewmodel Factory
    fun provideRecipeListViewmodelFactory(context: Context): RecipeListViewModelFactory {
        val repository = getPrivateRecipeRepository(context)
        return RecipeListViewModelFactory(repository)
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        val authentification = getAuthentification(context)
        return LoginViewModelFactory(
            authentification
        )
    }

    fun provideChangePasswordViewModelFactory(context: Context): ChangePasswordViewModelFactory {
        val authentification = getAuthentification(context)
        return ChangePasswordViewModelFactory(authentification)
    }

    fun provideCreateRecipeViewModelFactory(context: Context): CreateRecipeViewModelFactory {
        val privateRepository = getPrivateRecipeRepository(context)
        val publicRepository = getPublicRecipeRepository(context)
        return CreateRecipeViewModelFactory(privateRepository, publicRepository)
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
        val userRepo = getUserRepository(context)
        return AdminViewModelFactory(recipeRepo, userRepo)
    }

    fun provideProfileDisplayViewModelFactory(context: Context): ProfileDisplayViewModelFactory {
        val userRepository = getUserRepository(context)
        val recipeRepository = getPublicRecipeRepository(context)
        return ProfileDisplayViewModelFactory(userRepository, recipeRepository)
    }

    fun provideDisplaySearchListViewModelFactory(context: Context)
            :DisplaySearchListViewModelFactory {
        val repository = getPublicRecipeRepository(context)
        return DisplaySearchListViewModelFactory(repository)
    }

    fun provideSearchWithTagsViewModelFactory(context: Context):SearchWithTagsViewModelFactory {
        val repository = getEditTagRepository(context)
        return SearchWithTagsViewModelFactory(repository)
    }

    fun provideUserSearchViewModelFactory(context: Context):UserSearchViewModelFactory {
        val repository = getUserRepository(context)
        return UserSearchViewModelFactory(repository)
    }

    fun provideRecipeDisplayViewModelFactory(context: Context):RecipeDisplayViewModelFactory {
        val repo = getPublicRecipeRepository(context)
        return RecipeDisplayViewModelFactory(repo)
    }

    fun provideProfileEditViewModelFactory(context: Context):ProfileEditViewModelFactory {
        val repo = getUserRepository(context)
        return ProfileEditViewModelFactory(repo)
    }

    fun provideRegistrationViewModelFactory(context: Context):RegistrationViewModelFactory {
        val auth = getAuthentification(context)
        val repo = getUserRepository(context)
        return RegistrationViewModelFactory(auth, repo)
    }

}
