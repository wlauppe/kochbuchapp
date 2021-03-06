package de.psekochbuch.exzellenzkoch

import android.app.Application
import android.content.Context
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.FavouriteRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.TagFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationFakeImpl
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.*
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories.*


object InjectorUtils {

    private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
         val repo = PublicRecipeRepositoryImp.getInstance()
        if(AuthentificationImpl.isLogIn())
        {
            AuthentificationImpl.getToken(true){
                if(it != "") {
                    repo.setToken(it)
                } else {
                    repo.setToken(null)
                }
            }
        } else if (repo.isTokenSet())
        {
            repo.setToken(null)
        }
         
        return repo

        //return PublicRecipeFakeRepositoryImp.getInstance()
    }

    private fun getUserRepository(context: Context): UserRepository {
        //return UserFakeRepositoryImp.getInstance()
        val repo = UserRepositoryImp.getInstance()
        if(AuthentificationImpl.isLogIn())
        {
            AuthentificationImpl.getToken(true){
                if(it != "") {
                    repo.setToken(it)
                } else {
                    repo.setToken(null)
                }
            }
        }else if (repo.isTokenSet())
        {
            repo.setToken(null)
        }
       return repo
    }
    

    private fun getPrivateRecipeRepository(context: Context): PrivateRecipeRepository {
        //return PrivateRecipeFakeRepositoryImp.getInstance()
        return PrivateRecipeRepositoryImp.getInstance(context.applicationContext as Application)
    }

    private fun getFavouriteRecipeRepository(context: Context): FavouriteRecipeRepository {
        //return PrivateRecipeFakeRepositoryImp.getInstance()
        return FavouriteRecipeRepositoryImp.getInstance(context.applicationContext as Application)
    }

    private fun getEditTagRepository(context: Context): TagRepository {
        return TagFakeRepositoryImp()

    }

    // For Login, Register and ChangePassword
    private fun getAuthentification(context: Context): Authentification {
        return AuthentificationFakeImpl()
    }

    fun setToken(token:String?)
    {
        PublicRecipeRepositoryImp.getInstance().setToken(token)
        UserRepositoryImp.getInstance().setToken(token)
    }

    /*
     * Weitere Repos hier einfügen:
     * LOCAL: IngredientChapterRepository, PublicRecipeRepository, ShoppingListRepository
     * REMOTE: alle
     */

    //Beispiel für eine Viewmodel Factory
    fun provideRecipeListViewmodelFactory(context: Context): RecipeListViewModelFactory {
        val privateRepo = getPrivateRecipeRepository(context)
        val publicRepo = getPublicRecipeRepository(context)
        return RecipeListViewModelFactory(privateRepo, publicRepo)
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
        val userRepository = getUserRepository(context)
        return CreateRecipeViewModelFactory(privateRepository, publicRepository, userRepository)
    }

    //For Feed VM Factory
    fun provideFeedViewModelFactory(context: Context): FeedViewModelFactory {
        val repository = getPublicRecipeRepository(context)
        return FeedViewModelFactory(repository)
    }
    //Favourite VM Factory
    fun provideFavouriteViewModelFactory(context: Context): FavouriteViewModelFactory {
        val repository = getFavouriteRecipeRepository(context)
        val publicrepo = getPublicRecipeRepository(context)
        return FavouriteViewModelFactory(repository, publicrepo)
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
        val favouriteRepo = getFavouriteRecipeRepository(context)
        return RecipeDisplayViewModelFactory(repo, favouriteRepo)
    }

    fun provideProfileEditViewModelFactory(context: Context):ProfileEditViewModelFactory {
        val repo = getUserRepository(context)
        val publicRepo = getPublicRecipeRepository(context)
        val privateRepo = getPrivateRecipeRepository(context)
        return ProfileEditViewModelFactory(repo,publicRepo,privateRepo)
    }

    fun provideRegistrationViewModelFactory(context: Context):RegistrationViewModelFactory {
        val auth = getAuthentification(context)
        val repo = getUserRepository(context)
        return RegistrationViewModelFactory(auth, repo)
    }

}
