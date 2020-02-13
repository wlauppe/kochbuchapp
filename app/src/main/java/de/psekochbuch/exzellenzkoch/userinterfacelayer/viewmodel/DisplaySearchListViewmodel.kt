package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import java.util.*

class DisplaySearchListViewmodel(repo:PublicRecipeRepository) : ViewModel() {
    val TAG = "DisplaySearchListVM"

    var title: LiveData<String> = MutableLiveData<String>()
    var ingredients : LiveData<String> = MutableLiveData<String>()
    var tags: LiveData<String> = MutableLiveData<String>()

    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an IDs, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */
    val repository= repo

    var recipes : LiveData<List<PublicRecipe>> = MutableLiveData(emptyList())

    fun getPublicRecipes(title: String?, ingredients: String?, tags: String?):LiveData<List<PublicRecipe>> {
        var ingredientList = emptyList<String>()
        var tagList = emptyList<String>()
        if(ingredients != null) {
             ingredientList = ingredients.split(",")
        }
        if(tags != null) {
            var tagList = tags.split(",")
        }
        if(title == null){
            var title = ""
        }
       // return repository.search(title, ingredientList, tagList)
        // recipes = repository.search(title, ingredientList, tagList)
        recipes = repository.getPublicRecipes()
        if(recipes.value == null){
            Log.i(TAG, "Recipe is null!")
        }

        return recipes
    }

/*
    fun sortByVegan(): List<PublicRecipe>{
        val sortedList  = mutableListOf<PublicRecipe>()
        if(recipes.value != null) {
            for (recipe in recipes.value!!) {
                if(recipe.tags.contains("vegan")) {
                    sortedList.add(recipe)
                }
            }
        }
        return sortedList


    }
    fun sortByVegetarian() : List<PublicRecipe>{
        val sortedList  = mutableListOf<PublicRecipe>()
        if(recipes.value != null) {
            for (recipe in recipes.value!!) {
                if(recipe.tags.contains("vegetarisch")) {
                    sortedList.add(recipe)
                }
            }
        }
        return sortedList
    }
    fun sortByDate() {



    }
 */
}