package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import java.util.*

class DisplaySearchListViewmodel(repo:PublicRecipeRepository) : ViewModel() {


    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */
    val repository= repo
    var recipes : LiveData<List<PublicRecipe>> = repository.getPublicRecipes()

    fun searchRecipes(title: String?, ingredients: String?, tags: String?){
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