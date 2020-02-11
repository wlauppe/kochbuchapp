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
        //val list = listOf(recipe1, recipe2, recipe3, recipe4)
        // recipeList = mutableListOf<PublicRecipe>()

        Log.w(TAG, "Versuche auf Recipelistzuzugreifen")
        addToList(recipe1)
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
        val recipe = recipeList.get(recipeId)
        recipeList.remove(recipe)

    }

    override suspend fun updatePrivateRecipe(id: Int) {
        TODO()
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecipe(id: String): LiveData<PrivateRecipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PrivateRecipeRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PrivateRecipeFakeRepositoryImp().also { instance = it }
        }
    }
}
