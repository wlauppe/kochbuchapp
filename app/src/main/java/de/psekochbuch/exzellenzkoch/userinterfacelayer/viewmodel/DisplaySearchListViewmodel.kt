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



    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an IDs, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */
    val repository= repo

    var recipesFromServer :LiveData<List<PublicRecipe>> = MutableLiveData(emptyList())
    var recipes : MutableLiveData<List<PublicRecipe>> = MutableLiveData(emptyList())
    var recipesSortedTitle : MutableLiveData<List<PublicRecipe>> = MutableLiveData(emptyList())
    var recipesSortedDate : MutableLiveData<List<PublicRecipe>> = MutableLiveData(emptyList())

    fun getPublicRecipes(title: String?, ingredients: String?, tags: String?){
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

        recipesFromServer = repository.getPublicRecipes(title!!,tagList,ingredientList,null,"title")
        recipesFromServer.observeForever{
            recipes.postValue(it)
            recipesSortedTitle.postValue(it.sortedWith(object : Comparator<PublicRecipe>{
                override fun compare(recipe1: PublicRecipe?, recipe2: PublicRecipe?): Int {
                    if (recipe1 == null)
                        return -1
                    else if (recipe2 == null)
                        return 1
                    else
                        return recipe1.title.toLowerCase().compareTo(recipe2.title.toLowerCase())
                }
            }))
            recipesSortedDate.postValue(it.sortedWith(object : Comparator<PublicRecipe>{
                override fun compare(recipe1: PublicRecipe?, recipe2: PublicRecipe?): Int {
                    if (recipe1 == null)
                        return -1
                    else if (recipe2 == null)
                        return 1
                    else
                        return recipe1.getDateAsLong().compareTo(recipe2.getDateAsLong())
                }
            }))
        }

    }


    fun sortByVegan(): List<PublicRecipe>{
        var sortedList  = mutableListOf<PublicRecipe>()
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
        var sortedList  = mutableListOf<PublicRecipe>()
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
}