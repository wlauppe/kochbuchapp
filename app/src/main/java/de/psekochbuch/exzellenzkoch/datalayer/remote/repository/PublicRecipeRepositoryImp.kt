package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.PublicRecipeDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import java.io.File
import java.util.*


/**
 * The repository implementation has to fill the interfaces methods
 */
class PublicRecipeRepositoryImp : PublicRecipeRepository {

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

    override fun getPublicRecipes(
        tags: TagList,
        ingredients: IngredientChapter,
        creationDate: Date,
        sortOrder: String
    ): LiveData<List<PublicRecipe>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPublicRecipe(recipeId: Int): LiveData<PublicRecipe> {

        /* val recipe = PublicRecipe(2,title ="Test")
        val ld : MutableLiveData <PublicRecipe> = MutableLiveData(recipe)
        return ld */

        //    return LiveData<recipe
        val ldd = liveData(Dispatchers.IO, 20) {
            val dto = retrofit.getRecipe(recipeId)
            //val entity= PublicRecipeDtoEntityMapper().toEntity(dto)
            val recipe =
                PublicRecipe(0, "Test", ingredientChapter = listOf(), tags = listOf("sauer,salzig"))
            emit(recipe)
        }
        return ldd
    }


    override fun deleteRecipe(recipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setImage(recipeId: Int, Image: File) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportRecipe(RecipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}




