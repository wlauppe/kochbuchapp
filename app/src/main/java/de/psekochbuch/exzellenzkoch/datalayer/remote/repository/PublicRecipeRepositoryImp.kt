package de.psekochbuch.exzellenzkoch.datalayer.remote.repository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.PublicRecipeDtoEntityMapper
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.UserDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withTimeout
import java.io.File
import java.lang.NullPointerException
import java.util.*


/**
 * The repository implementation has to fill the interfaces methods
 */
class PublicRecipeRepositoryImp : PublicRecipeRepository {
    val recipeMapper = PublicRecipeDtoEntityMapper()

    val token = null
    //TODO token von Authentification Interface bekommen.

    val retrofit: PublicRecipeApi =
        ApiServiceBuilder(token).createApi(PublicRecipeApi::class.java) as PublicRecipeApi

    override suspend fun removePublicRecipe(recipe: PublicRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws
    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {

        //val searchList= retrofit.search(page=1,readCount = 1000)

        //val dto = retrofit.getRecipe(1)
        val recipe1 = PublicRecipe(0,"Test", ingredientChapter=listOf(), tags=listOf("sauer,salzig"))
        val recipe2 = PublicRecipe(0,"Test", ingredientChapter=listOf(), tags=listOf("sauer,salzig"))

        val list = listOf(recipe1, recipe2)
        val ld : MutableLiveData<List<PublicRecipe>> = MutableLiveData(list)
        return ld
    }

    override fun getPublicRecipes(tags:TagList, ingredients: IngredientChapter, creationDate:Date, sortOrder:String ): LiveData<List<PublicRecipe>>{
        TODO()
        //die ganzen optional sachen brauchen api 24
        try{
            // retrofit.search(Optional.of("titell"),)
        } catch (error: Throwable) {
            throw NetworkError("Unable to write this method", error)
        }
    }

    override suspend fun getPublicRecipe(recipeId: Int): LiveData<PublicRecipe> {
        try{
            return recipeMapper.toLiveEntity(retrofit.getRecipe(recipeId).body()!!)
        } catch(error: Exception){
            throw NetworkError("Server sent Nullpointer",error)
        }
        catch (error: Throwable) {
            throw NetworkError("Unable to get recipe with id:" + recipeId, error)
        }
    }



    override suspend fun  deleteRecipe(recipeId: Int) {
        try {
            val result = withTimeout(5_000) {
                retrofit.deleteRecipe(recipeId)
            }
        } catch (error: Throwable) {
            throw NetworkError("Unable to delete recipe", error)
        }
    }


    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {
        try{
            coroutineScope{retrofit.addRecipe(recipeMapper.toDto(publicRecipe))}
        } catch (error: Throwable) {
            throw NetworkError("Unable to publish recipe", error)
        }
        //Soll der rückgabewert die id des rezpetes sein?
        return 0
    }


    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setImage(recipeId: Int, Image: File) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportRecipe(recipeId: Int) {
        try{
            coroutineScope{retrofit.reportRecipe(recipeId)}
        } catch (error: Throwable) {
            throw NetworkError("Unable to report recipe", error)
        }
    }

    override suspend fun unreportRecipe(RecipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PublicRecipeRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PublicRecipeRepositoryImp().also { instance = it }
            }
    }
}




