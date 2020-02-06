package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.Unit
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class PublicRecipeFakeRepositoryImp() : PublicRecipeRepository {
    suspend override  fun removePublicRecipe(recipe: PublicRecipe) {

    }

    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        val recipe1 = PublicRecipe(1,"trockener Sandkuchen")
        val recipe2 = PublicRecipe(2,"Quiche", imgUrl = "file:///android_asset/exampleimages/quiche.png")

        var munit = Unit.EssLöffel
        var ingredient = IngredientAmount("ingredientAmount", 4.4, munit)
        var ingredientChapter = IngredientChapter("ingredientChapter", listOf(ingredient))
        var listTags = listOf<String>("tag2", "tag 4","tag2", "tag 4","tag2", "tag 4")
        var recipe4 = PublicRecipe(4, "Tabak", "zutatenText hier kann alles drinnstehen", listOf(ingredientChapter), listTags,"zubereitungsbeschreibung hier kann auch alles stehen",imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val recipe3 = PublicRecipe(3,"Bratapfel", imgUrl = "file:///android_asset/exampleimages/bratapfel.png")
        val list = listOf(recipe1,recipe2, recipe3, recipe4)

        val ld : MutableLiveData <List<PublicRecipe>> = MutableLiveData(list) //.apply { list }
        return ld
    }

   companion object {

        // For Singleton instantiation
        @Volatile private var instance: PublicRecipeRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PublicRecipeFakeRepositoryImp().also { instance = it }
            }
    }
}