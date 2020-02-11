package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import java.security.Timestamp
import java.util.*

class PrivateRecipeFakeRepositoryImp : PrivateRecipeRepository {
    private val TAG = "PrivateFakeImp"
    private var recipeList: MutableList<PrivateRecipe> = mutableListOf()

    private val creationTimestamp: Date = Date()

    //helper function die beim Adden die ids dass sie richtig sind anpasst.
    private fun addToList (recipe : PrivateRecipe){
        var myrecipe = recipe
        val newid=recipeList.size+1
        myrecipe.recipeId=newid
        recipeList.add(myrecipe)

    }

    init {
        Log.w(TAG, "Starte init Block")
        val recipe1:PrivateRecipe = PrivateRecipe(0, "Kartoffelklöße",
            "3 große Kartoffeln", listOf("herzhaft, deftig"), "kochen",
            "file:///android_asset/exampleimages/quiche.png", 20, 24, creationTimestamp,
            5)

        var zubereitungsString = "Step 1    \n" +
                "Bring a large pot of salted water to a boil. In a medium bowl, beat the eggs with the milk, cottage cheese, 1/2 teaspoon of salt and 1/4 teaspoon of pepper. Stir in the flour until a smooth, thick, sticky batter forms.\n" +
                "\n" +
                "Step 2    \n" +
                "Spoon the batter into a colander with 1/4-inch holes. Set or hold the colander 1 inch above the boiling water and scrape the batter through the holes, using a rubber spatula. Stir the spaetzle once or twice to separate them. As soon as they rise to the surface, use a slotted spoon to transfer the spaetzle to a clean colander and drain well.\n" +
                "\n" +
                "Step 3    \n" +
                "Melt the butter in a large nonstick skillet. Add the boiled spaetzle and cook them over moderately high heat, stirring and shaking the skillet occasionally, until the spaetzle are browned and crisp in spots, about 5 minutes. Add the quark and snipped chives, reduce the heat to moderately low and cook, stirring, until the sauce is creamy, 1 to 2 minutes. Season the spaetzle with salt and pepper and serve right away."
        val recipe2:PrivateRecipe = PrivateRecipe(1, "Spätzle",
            "Alles was gut schmeckt", listOf("herzhaft, deftig"), zubereitungsString,
            "https://cdn-image.foodandwine.com/sites/default/files/styles/4_3_horizontal_-_1200x900/public/200605-r-xl-fresh-cheese-spaetzle.jpg?itok=6MiKrIkI", 20, 24, creationTimestamp,
            5)

        //val list = listOf(recipe1, recipe2, recipe3, recipe4)
        // recipeList = mutableListOf<PublicRecipe>()

        Log.w(TAG, "Versuche auf Recipelistzuzugreifen")
        addToList(recipe1)
        addToList(recipe2)
        Log.w(TAG, "Habe habe jetzt zugegriffen")
    }


    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        Log.w(TAG, "Auf get Public Recipes wurde jetzt zugegriffen")
        val entries = recipeList.size
        Log.w(TAG, "entries = $entries")
        Log.w(TAG, "Gebe Folgende Werte zurück")

        for(recipe in recipeList) {
            Log.w(TAG, "rezept id: ${recipe.recipeId}, Titel ${recipe.title}, ImageURL ${recipe.imgUrl}")
        }

        Log.w(TAG, "Id von erstem aus der List ist $recipeList[1].recipeId()")
        val ld: MutableLiveData<List<PrivateRecipe>> = MutableLiveData(recipeList)
        return ld

    }

    override fun getPrivateRecipe(recipeId: Int): LiveData<PrivateRecipe> {
        for(recipe in recipeList){
            if (recipe.recipeId == recipeId){
                return MutableLiveData(recipe)
            }
        }
        val recipe = recipeList.get(recipeId)
        val ld: MutableLiveData<PrivateRecipe> = MutableLiveData(recipe)
        return ld
    }

    override suspend fun deletePrivateRecipe(recipeId: Int) {
        val recipe = recipeList.get(recipeId-1)
        recipeList.remove(recipe)

    }

    override suspend fun updatePrivateRecipe(privateRecipe: PrivateRecipe) {
        recipeList.set(privateRecipe.recipeId,privateRecipe)

    }


    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        addToList(privateRecipe)

    }

    override fun getRecipe(recipeId: Int): LiveData<PrivateRecipe> {

        for(recipe in recipeList){
            if (recipe.recipeId == recipeId){
                return MutableLiveData(recipe)
            }
        }
        val recipe = recipeList.get(recipeId)
        val ld: MutableLiveData<PrivateRecipe> = MutableLiveData(recipe)
        return ld


    }
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PrivateRecipeRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PrivateRecipeFakeRepositoryImp().also { instance = it }
        }
    }
}
