package de.psekochbuch.exzellenzkoch.datalayer.remote.repository
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.BuildConfig

import de.psekochbuch.exzellenzkoch.PAGE_SIZE
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.AdminApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.FileApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.FileDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.PublicRecipeDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeout
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import java.util.concurrent.Semaphore
import java.util.concurrent.locks.ReentrantLock


/**
 * The repository implementation has to fill the interfaces methods
 */
class PublicRecipeRepositoryImp : PublicRecipeRepository {
    private val TAG = "PublicRealImp"
    val recipeMapper = PublicRecipeDtoEntityMapper()

    private var token :String? = null
    //TODO token von Authentification Interface bekommen.

    private var errorRecipe = PublicRecipe(0, "Error Fetching Recipe!", imgUrl = "file:///android_asset/exampleimages/error.png",creationTimeStamp = Date(0))

    //private val untilThreadStartsLock = Semaphore(1)
    //private val workLock = ReentrantLock(true)

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
                dtoList.let {
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
    override fun getPublicRecipes(page:Int): LiveData<List<PublicRecipe>> {
        Log.w(TAG, "getPublicRecipes() wird aufgerufen")
        val lData = liveData(Dispatchers.IO, 1000) {
            Log.w(TAG, "jetzt bin ich im Coroutine Scope")
            try {
                val dtoList =
                    recipeApiService.search(null, null, null, null, page, PAGE_SIZE)
                //if (!response.isSuccessful) throw error("response not successful")
                dtoList.let {
                    val entityList = PublicRecipeDtoEntityMapper().toListEntity(dtoList)
                    // emit in reversed order to show most recent one first
                    emit(entityList)
                }
            }
             catch(error : Throwable) {
                 emit(listOf(errorRecipe))
             }
        }
        return lData

    }

    override fun getPublicRecipes(title:String, tags:List<String>, ingredients: List<String>, creationDate:Date?, sortOrder:String,page: Int ): LiveData<List<PublicRecipe>>
    {
        Log.w(TAG, "getPublicRecipes(parameter) wird aufgerufen")
        val lData = liveData(Dispatchers.IO, 1000) {
            Log.w(TAG, "jetzt bin ich im Coroutine Scope")
            try {
                val dtoList =
                    recipeApiService.search(title, tags, ingredients, creationDate,page,
                        PAGE_SIZE)
                //if (!response.isSuccessful) throw error("response not successful")
                dtoList.let {
                    val entityList = PublicRecipeDtoEntityMapper().toListEntity(dtoList)
                    emit(entityList)
                }
            }
            catch(error : Throwable) {
                emit(listOf(errorRecipe))
            }
        }
        return lData

    }

    override fun getPublicRecipes(
        title: String,
        tags: List<String>,
        ingredients: List<String>,
        creationDate: Date?,
        sortOrder: String
    ): LiveData<List<PublicRecipe>> {
        return getPublicRecipes(title,tags,ingredients,creationDate,sortOrder,1)
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
                emit(errorRecipe)
            }
        }
        return lData
    }


    @Throws
    override suspend fun  deleteRecipe(recipeId: Int) {
        Log.w(TAG, "deleteRecipe wird aufgerufen")
        try {
            val result = withTimeout(5000) {
                recipeApiService.deleteRecipe(recipeId)
            }
        } catch (error: Throwable) {
           // throw NetworkError("Unable to delete recipe", error)
        }
    }

    @Throws
    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {

        var returnId : Int = 0
        Log.w(TAG, "publishRecipe() wird aufgerufen für recipe mit titel = ${publicRecipe.title} und img=${publicRecipe.imgUrl} id=${publicRecipe.recipeId}")

            val recipeId=publicRecipe.recipeId
            if (recipeId != 0) {
                //fileApiService.deleteImage() koennte auch ausgefuehrt werden.
               try {
                   recipeApiService.deleteRecipe(recipeId)
               }
               catch  (error : Throwable) {
                   //manchmal kommt es vor, dass das veröffentlichte Rezept nicht existiert, dann kann es nicht gelöscht werden
                   //das ist ok.
               }
            }

            var response: FileDto
            val remoteUrl : String


                //First upload the Image.
                if(publicRecipe.imgUrl != "") {
                    try {
                        val file: File = File(publicRecipe.imgUrl)
                        Log.i(TAG, "ImgUrl is ${publicRecipe.imgUrl}")
                        val ex = file.exists()
                        if (!ex) {
                            throw NetworkError(
                                "cant publish image, it doesnt exist ${publicRecipe.imgUrl}",
                                Error()
                            )
                        }
                        val body = RequestBody.create(MediaType.parse("*/*"), file)
                        val multi = MultipartBody.Part.createFormData("file", file.name, body)
                        val requestFile: RequestBody =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        response = fileApiService.addImage(multi)

                        val remoteUrl = response.filePath
                        //speichere filepath in recipe
                        //TODO Muss noch Mapper schreiben, dass URL gemappt wird.
                        publicRecipe.imgUrl = BuildConfig.IMG_PREFIX + remoteUrl
                    } catch (error: Throwable) {
                        throw NetworkError("Unable to publish recipe image", error)
                    }
                }
                try {
                    //TODO Baseurl hinzufügen eventuell in den Mapper.

                    val returnDto = recipeApiService.addRecipe(recipeMapper.toDto(publicRecipe))
                    returnId = returnDto.id
                }
                catch (error : Throwable) {
                    throw NetworkError("Unable to publish recipe", error)
                }

        //das ist der Rückgabewert der


        return returnId
    }


    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("Wunschkriterium, wird hier und vom Server nicht implementiert")
    }

    override suspend fun setImage(recipeId: Int, ImageUrl: String) {
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


    override suspend fun unreportRecipe(recipeId: Int) {
        coroutineScope {
            try {
                adminApiService.deReportPublicRecipe(recipeId)
            } catch (error: Throwable) {
                //throw NetworkError("Unable to publish report recipe", error)
            }
        }
    }

    override fun getRecipesFromUser(userId: String): LiveData<List<PublicRecipe>> {
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


    override fun setToken(tk:String?)
    {
        token = tk
        recipeApiService = ApiServiceBuilder(token).createApi(PublicRecipeApi::class.java) as PublicRecipeApi
        fileApiService = ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi
        adminApiService = ApiServiceBuilder(token).createApi(AdminApi::class.java) as AdminApi
    }

    override fun isTokenSet(): Boolean {
        if(token != null)
        {
            return true
        }
        return false
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




