package de.psekochbuch.exzellenzkoch.datalayer.remote.repository
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.AdminApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.FileApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.PublicRecipeDtoEntityMapper
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.UserDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
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

    private var token :String? = null
    //TODO token von Authentification Interface bekommen.

    var recipeApiService: PublicRecipeApi =
        ApiServiceBuilder(token).createApi(PublicRecipeApi::class.java) as PublicRecipeApi

    var fileApiService: FileApi =
        ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi

    var adminApiService: AdminApi =
        ApiServiceBuilder(token).createApi(AdminApi::class.java) as AdminApi


    override fun getReportedPublicRecipes(): LiveData<List<PublicRecipe>> {
        val lData = liveData(Dispatchers.IO, 1000) {
            Log.w(TAG, "jetzt bin ich im Coroutine Scope")
            try {
                val dtoList =
                    adminApiService.getReportedPublicRecipes(1, 100)
                dtoList?.let {
                    val entityList = PublicRecipeDtoEntityMapper().toListEntity(dtoList)
                    emit(entityList)
                }
            } catch (error: Throwable) {
                val list=listOf(
                    PublicRecipe(
                        0,
                        title = "No reported recipes found",
                        imgUrl = "file:///android_asset/exampleimages/checkmark.png"
                    )
                )
                emit(list)
            }


        }
        return lData


    }


    //Dies ist die normale Funktion die Search benutzt.
    @Throws
    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        try {
            Log.w(TAG, "getPublicRecipes() wird aufgerufen")
            val lData = liveData(Dispatchers.IO, 1000) {
                Log.w(TAG, "jetzt bin ich im Coroutine Scope")
                try {
                    val dtoList =
                        recipeApiService.search(null, null, null, null, 1, 100)
                    //if (!response.isSuccessful) throw error("response not successful")
                    dtoList?.let {
                        val entityList = PublicRecipeDtoEntityMapper().toListEntity(dtoList)
                        emit(entityList)
                    }
                }
                 catch(error : Throwable) {
                     emit(listOf(PublicRecipe(0, "Error Fetching Recipes!", imgUrl = "file:///android_asset/exampleimages/error.png")))
                 }

            }
            return lData
        }
        catch(error : Throwable) {
            return liveData {
                emit(listOf(PublicRecipe(0, "Error Fetching Recipes")))
            }
        }
    }

    override fun getPublicRecipes(tags:TagList, ingredients: IngredientChapter, creationDate:Date, sortOrder:String ): LiveData<List<PublicRecipe>>
    {
        Log.w(TAG, "getPublicRecipes(tags ... ) wird aufgerufen")
        Log.w(TAG,"Parameter:  tags: $tags, ingredients: $ingredients, creationDate: $creationDate, sortOrder: $sortOrder")
        var recipes = getPublicRecipes()
        return recipes
    }



    override fun getPublicRecipe(recipeId: Int): LiveData<PublicRecipe> {
        Log.w(TAG, "getPublicRecipe() wird aufgerufen mit id $recipeId")

        val lData = liveData(Dispatchers.IO, 1000) {
            try {
                val dto = recipeApiService.getRecipe(recipeId)
                //if (!response.isSuccessful) throw error("response not successful")
                val entity = PublicRecipeDtoEntityMapper().toEntity(dto)
                emit(entity)
            }
            catch(error : Throwable) {
                emit(PublicRecipe(0, "Error Fetching Recipe!", imgUrl = "file:///android_asset/exampleimages/error.png"))
            }


        }
        return lData
    }


    @Throws
    override suspend fun  deleteRecipe(recipeId: Int) {
        try {
            val result = withTimeout(1000) {
                recipeApiService.deleteRecipe(recipeId)
            }
        } catch (error: Throwable) {
            throw NetworkError("Unable to delete recipe", error)
        }
    }

    @Throws
    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {
        var returnId : Int = 0
            coroutineScope{
                try {
                    //First upload the Image.
                    val file : File = File(publicRecipe.imgUrl)
                    val body = RequestBody.create(MediaType.parse("image/*"), file)
                    val multi = MultipartBody.Part.createFormData("file", file.name, body)
                    //val requestFile : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    val response = fileApiService.addImage(multi)
                    //TODO Baseurl hinzufügen eventuell in den Mapper.
                    val remoteUrl = response.filePath
                    //speichere filepath in recipe
                    //publicRecipe.imgUrl=
                    val returnDto = recipeApiService.addRecipe(recipeMapper.toDto(publicRecipe))
                    returnId = returnDto.id

                }
                catch (error : Throwable) {
                    throw NetworkError("Unable to publish recipe", error)
                }

            }

        //das ist der Rückgabewert der
        return returnId
    }


    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("Wunschkriterium, wird hier und vom Server nicht implementiert")
    }

    override suspend fun setImage(recipeId: Int, ImageUrl: String) {

        val file : File = File(ImageUrl)

        val body = RequestBody.create(MediaType.parse("image/*"), file)
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
        coroutineScope {
            try {
                //TODO Implement Admin Api.
            } catch (error: Throwable) {
                throw NetworkError("Unable to publish report recipe", error)
            }
        }
    }

    override fun getRecipesFromUser(userId: String): LiveData<List<PublicRecipe>> {
        try {
            Log.w(TAG, "getPublicRecipes() wird aufgerufen")
            val lData = liveData(Dispatchers.IO, 1000) {
                Log.w(TAG, "jetzt bin ich im Coroutine Scope")
                try {
                    val dtoList =
                        recipeApiService.getUserRecipes(userId)
                    //if (!response.isSuccessful) throw error("response not successful")
                    dtoList?.let {
                        val entityList = PublicRecipeDtoEntityMapper().toListEntity(dtoList)
                        emit(entityList)
                    }
                }
                catch(error : Throwable) {
                    error.printStackTrace()
                    emit(listOf(PublicRecipe(0, "Error Fetching Recipes!", imgUrl = "file:///android_asset/exampleimages/error.png")))
                }

            }
            return lData
        }
        catch(error : Throwable) {
            return liveData {
                emit(listOf(PublicRecipe(0, "Error Fetching Recipes")))
            }
        }
    }

    override fun setToken(tk:String?)
    {
        token = tk
        recipeApiService = ApiServiceBuilder(token).createApi(PublicRecipeApi::class.java) as PublicRecipeApi
        fileApiService = ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi
        adminApiService = ApiServiceBuilder(token).createApi(AdminApi::class.java) as AdminApi
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




