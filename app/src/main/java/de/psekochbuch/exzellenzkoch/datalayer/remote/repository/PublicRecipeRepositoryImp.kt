package de.psekochbuch.exzellenzkoch.datalayer.remote.repository
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.FileApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
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
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.lang.NullPointerException
import java.lang.Thread.sleep
import java.util.*


/**
 * The repository implementation has to fill the interfaces methods
 */
class PublicRecipeRepositoryImp : PublicRecipeRepository {
    private val TAG = "PublicRealImp"
    val recipeMapper = PublicRecipeDtoEntityMapper()

    val token = null
    //TODO token von Authentification Interface bekommen.

    val recipeApiService: PublicRecipeApi =
        ApiServiceBuilder(token).createApi(PublicRecipeApi::class.java) as PublicRecipeApi

    val fileApiService: FileApi =
        ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi


    @Throws
    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        Log.w(TAG, "getPublicRecipes() wird aufgerufen")
        val lData = liveData(Dispatchers.IO, 1000) {
            Log.w(TAG, "jetzt bin ich im Coroutine Scope")
            val response =
                recipeApiService.search(null, null, null, null, 1, 100)
            if (!response.isSuccessful) throw error("response not successful")
            val entityList = PublicRecipeDtoEntityMapper().toListEntity(response.body()!!)
            emit(entityList)
        }
        return lData
    }

    override fun getPublicRecipes(tags:TagList, ingredients: IngredientChapter, creationDate:Date, sortOrder:String ): LiveData<List<PublicRecipe>>{
        TODO("implementieren")
        try{


        } catch (error: Throwable) {
            throw NetworkError("Unable to write this method", error)
        }
    }


    override fun getPublicRecipe(recipeId: Int): LiveData<PublicRecipe> {

    //Jetzt mal mit LiveData Builder
        val lData = liveData(Dispatchers.IO, 1000) {
            val response = recipeApiService.getRecipe(recipeId)
            if (!response.isSuccessful) throw error("response not successful")
            val entity = PublicRecipeDtoEntityMapper().toEntity(response.body()!!)
            emit(entity)
        }
    //return MutableLiveData<PublicRecipe>(PublicRecipe(0,"Title"))
        return lData
    }

       /* try{
            return recipeMapper.toLiveEntity(recipeApiService.getRecipe(recipeId))
        } catch(error: NullPointerException){
            throw NetworkError("Server sent Nullpointer",error)
        }
        catch (error: Throwable) {
            throw NetworkError("Unable to get recipe with id:" + recipeId, error)
        }
    }*/



    override suspend fun  deleteRecipe(recipeId: Int) {
        try {
            val result = withTimeout(5_000) {
                recipeApiService.deleteRecipe(recipeId)
            }
        } catch (error: Throwable) {
            throw NetworkError("Unable to delete recipe", error)
        }
    }


    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {
        var returnId : Int = -1
        try{
            coroutineScope{
                val returnDto = recipeApiService.addRecipe(recipeMapper.toDto(publicRecipe))
                returnId = returnDto.id
            }

        } catch (error: Throwable) {
            throw NetworkError("Unable to publish recipe", error)
        }

        //Soll der rückgabewert die id des rezpetes sein? ja
        return returnId
    }


    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setImage(recipeId: Int, ImageUrl: String) {
        //versuche es erst mal mit einem vordefinierten Image
        //später könnte man direkt ImageUrl übergeben.
        //val CustomUrl = "file:///android_asset/exampleimages/quiche.png"
        val file : File = File(ImageUrl)

        val body = RequestBody.create(MediaType.parse("*/*"), file)
        val multi = MultipartBody.Part.createFormData("file", file.name, body)

        //val requestFile : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        fileApiService.addImage(multi)
    }

    override suspend fun reportRecipe(recipeId: Int) {
        try{
            coroutineScope{recipeApiService.reportRecipe(recipeId)}
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




