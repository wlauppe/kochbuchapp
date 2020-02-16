package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import java.util.*

/**
 * The DisplaySearchListViewmodel handles the information for the DisplaySearchListFragment.
 * @param repo: the repository for the public recipes. Through the repository the methods can access
 * the database.
 */
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
             ingredientList = ingredients.split(", ")
        }
        if(tags != null) {
            tagList = tags.split(", ")
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
}