package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class DisplaySearchListViewmodel : ViewModel() {

    //var fakreRepo: PublicRecipeFakeRepository = PublicRecipeFakeRepository()
    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */
    val repository=PublicRecipeFakeRepositoryImp()
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
    fun sortBy(sortOption:String){

    }
}