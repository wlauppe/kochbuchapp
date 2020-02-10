package de.psekochbuch.exzellenzkoch.datalayer.remote.api

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*



/**
 * Interface for the api from the picturesfiles
 */

// TODO Serverapi anpassen

/**
 * Interface for the api from the picturesfiles
 */

interface FileApi {

    /**
     * GET-Request to gets the file with the url
     * The URL ends with api/images/userid/imageName
     * @param imageName Name form the picture
     * @param userId The id of the user, which uploaded the picture
     * @return The picture
     */

    //@GET("/{userId}/{imageName}")
    //fun getImage(@Path( "imageName") imageName : String, @Path("userId") userId : String) : Response<LiveData<>>

    /**
     * POST-Request to add an image to a recipe
     * The URL ends with api/images/recipeId
     * @param file Picture to store
     * @return The Online-Url of the File
     */

    @PostMapping("")
    @ResponseBody
    fun addImage(@RequestParam("file") file: MultipartFile) :FileDto?

    /**
     * PUT-Request to update an image from a recipe
     * The URL ends with api/images/recipeId
     * @param file Picture to update
     * @param imageName Name form the picture
     * @param userId The id of the user, which uploaded the picture
     * @return The Online-Url of the File
     */
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
    @DeleteMapping("/{userId}/{imageName}")
    fun deleteRecipe(@PathVariable imageName:String, @PathVariable userId:String) : FileDto?
}
