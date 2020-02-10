package de.psekochbuch.exzellenzkoch.datalayer.remote.api

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.FileDto
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*




// TODO Serverapi anpassen

/**
 * Interface for the api from the picturesfiles
 */

interface FileApi {

    /* GET Brauchen wir erst mal nicht, das macht Glide.
     * GET-Request to gets the file with the url
     * The URL ends with api/images/userid/imageName
     * @param imageName Name form the picture
     * @param userId The id of the user, which uploaded the picture
     * @return The picture
     */

    //@GET("/{userId}/{imageName}")
    //fun getImage(@Path( "imageName") imageName : String, @Path("userId") userId : String) : Response<LiveData<>>

    /*
     * POST-Request to add an image to a recipe
     * The URL ends with api/images/recipeId
     * @param file Picture to store
     * @return The Online-Url of the File
     */


    //pass it like this
    /*File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
    RequestBody requestFile =
    RequestBody.create(MediaType.parse("multipart/form-data"), file) */


    @DELETE("/{userId}/{imageName}")
    suspend fun deleteRecipe(@Path("imageName") imageName: String, @Path("userId") userId: String): Response<FileDto>


    @Multipart
    @POST("api/images")
    fun addImage(@Part("image\;filename=file.jpg\"") file: RequestBody)

}

    /**
     * PUT-Request to update an image from a recipe
     * The URL ends with api/images/recipeId
     * @param file Picture to update
     * @param imageName Name form the picture
     * @param userId The id of the user, which uploaded the picture
     * @return The Online-Url of the File
     */

/* PUT brauchen wir auch erst mal nicht, können ja die URL neu setzen
    @PutMapping("/{userId}/{imageName}")
    @ResponseBody
    fun updateImage(@RequestParam("file") file: MultipartFile, @PathVariable imageName:String, @PathVariable userId:String) :FileDto?

    /**
     * DELETE-Request to delete an image from a recipe
     * The URL ends with api/images/recipeId
     * @param imageName Name form the picture
     * @param userId The id of the user, which uploaded the picture
     * @return Empty Url
     */
    */



*/