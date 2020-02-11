package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.Unit
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

import java.util.*
import kotlin.Int
import kotlin.String
import kotlin.TODO
import kotlin.also
import kotlin.synchronized


class PublicRecipeFakeRepositoryImp() : PublicRecipeRepository {
    private val TAG = "FakeRepositoryImp"
    private var recipeList: MutableList<PublicRecipe> = mutableListOf()
    var entries = 1
    //var recipeList : MutableList<PublicRecipe>

    fun initialise() {
        Log.w(TAG, "Starte init Block")
        val recipe1 = PublicRecipe(1, "trockener Sandkuchen")
        val recipe2 =
            PublicRecipe(2, "Quiche", imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val munit = Unit.EssLöffel
        val ingredient = IngredientAmount("ingredientAmount", 4.4, munit)
        val ingredientChapter = IngredientChapter(4, "test", listOf(ingredient))
        val listTags = listOf<String>("tag2", "tag 4", "tag2", "tag 4", "tag2", "tag 4")
        val recipe4 = PublicRecipe(
            4,
            "Tabak",
            "zutatenText hier kann alles drinnstehen",
            listOf(ingredientChapter),
            listTags,
            "zubereitungsbeschreibung hier kann auch alles stehen",
            imgUrl = "file:///android_asset/exampleimages/quiche.png"
        )

        val recipe3 = PublicRecipe(
            3,
            "Bratapfel",
            imgUrl = "file:///android_asset/exampleimages/bratapfel.png"
        )
        val list = listOf(recipe1, recipe2, recipe3, recipe4)
        var recipeList: MutableList<PublicRecipe> = mutableListOf<PublicRecipe>()
        Log.w(TAG, "Versuche auf Recipelistzuzugreifen")
        recipeList.add(recipe1)
        recipeList.add(recipe2)
        recipeList.add(recipe3)
        recipeList.add(recipe4)
        Log.w(TAG, "Habe habe jetzt zugegriffen")
    }


    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        /*val recipe1 = PublicRecipe(1,"trockener Sandkuchen")
        val recipe2 = PublicRecipe(2,"Quiche", imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val munit = Unit.EssLöffel
        val ingredient = IngredientAmount("ingredientAmount", 4.4, munit)
        val ingredientChapter = IngredientChapter(4, "test", listOf(ingredient))
        val listTags = listOf<String>("tag2", "tag 4","tag2", "tag 4","tag2", "tag 4")
        val recipe4 = PublicRecipe(4, "Tabak", "zutatenText hier kann alles drinnstehen", listOf(ingredientChapter), listTags,"zubereitungsbeschreibung hier kann auch alles stehen",imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val recipe3 = PublicRecipe(3,"Bratapfel", imgUrl = "file:///android_asset/exampleimages/bratapfel.png")
        val list = listOf(recipe1,recipe2, recipe3, recipe4)


         */
        Log.w(TAG, "Auf get Public Recipes wurde jetzt zugegriffen")
        entries = recipeList.size
        Log.w(TAG, "entries = $entries")
        Log.w(TAG, "Gebe Folgende Werte zurück")

        for(recipe in recipeList) {
            Log.w(TAG, "rezept id: ${recipe.recipeId}, Titel ${recipe.title}, ImageURL ${recipe.imgUrl}")
        }

        Log.w(TAG, "Id von erstem aus der List ist $recipeList[1].recipeId()")

        val recipe1 = PublicRecipe(1, "extra tockener Sandkuchen")
        recipeList.add(recipe1)
        val ld: MutableLiveData<List<PublicRecipe>> = MutableLiveData(recipeList) //.apply { list }
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
        val recipe = recipeList.get(recipeId)
        val ld: MutableLiveData<PublicRecipe> = MutableLiveData(recipe)
        return ld
    }

    override suspend fun deleteRecipe(recipeId: Int) {
        val recipe = recipeList.get(recipeId)
        recipeList.remove(recipe)
    }

    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setImage(recipeId: Int, ImageUrl: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportRecipe(RecipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun unreportRecipe(RecipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: PublicRecipeRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PublicRecipeFakeRepositoryImp().also { instance = it }
        }
    }
}