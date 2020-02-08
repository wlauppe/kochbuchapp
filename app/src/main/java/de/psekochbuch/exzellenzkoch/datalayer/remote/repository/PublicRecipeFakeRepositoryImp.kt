package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.Unit
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import java.io.File
import java.util.*


class PublicRecipeFakeRepositoryImp() : PublicRecipeRepository {
    suspend override  fun removePublicRecipe(recipe: PublicRecipe) {

    }

    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        val recipe1 = PublicRecipe(1,"trockener Sandkuchen")
        val recipe2 = PublicRecipe(2,"Quiche", imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val munit = Unit.EssLöffel
        val ingredient = IngredientAmount("ingredientAmount", 4.4, munit)
        val ingredientChapter = IngredientChapter(4, "test", listOf(ingredient))
        val listTags = listOf<String>("tag2", "tag 4","tag2", "tag 4","tag2", "tag 4")
        val recipe4 = PublicRecipe(4, "Tabak", "zutatenText hier kann alles drinnstehen", listOf(ingredientChapter), listTags,"zubereitungsbeschreibung hier kann auch alles stehen",imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val recipe3 = PublicRecipe(3,"Bratapfel", imgUrl = "file:///android_asset/exampleimages/bratapfel.png")
        val list = listOf(recipe1,recipe2, recipe3, recipe4)

        val ld : MutableLiveData <List<PublicRecipe>> = MutableLiveData(list) //.apply { list }
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override suspend fun unreporRecipe(RecipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PublicRecipeRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
                instance ?: PublicRecipeFakeRepositoryImp().also { instance = it }
            }
   }
}