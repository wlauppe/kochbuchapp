package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import java.util.*

class RecipeDisplayViewmodel(repository:PublicRecipeRepository) : ViewModel() {

    var repo = repository


    //Das Fragment wird nur aufgerufen wenn ein Rezept ausgewählt wird. Daher nicht lateinit

    var recipe :PublicRecipe? = null

//LiveData Attributes
     var image: MutableLiveData<String?> = MutableLiveData(recipe?.imgUrl)
     var title: LiveData<String> = MutableLiveData(recipe?.title)
     var preparationDescription: LiveData<String> = MutableLiveData(recipe?.preparation)
     var ingredientChapter= MutableLiveData(recipe?.ingredientChapter)
      var tagsList: LiveData<List<String>> = MutableLiveData(recipe?.tags)
      var recipeCookTime: LiveData<Int>  =MutableLiveData(recipe?.cookingTime)
      var recipePrepTime: LiveData<Int> = MutableLiveData(recipe?.preparationTime)
      var creationTime: LiveData<Date> = MutableLiveData(recipe?.creationTimeStamp)
    var creationDate = "Erstellungsdatum: 2020"
      //var rating: LiveData<Double> = MutableLiveData(recipe.rating)

    //Attributes
    var id = 0

    var ingredients = getIngredientsStrings()


   private fun getIngredientsStrings():String{
        var result= ""
       if(recipe != null){
           for(ingredient in recipe!!.ingredientChapter){
               for(ingredient in ingredient.ingredients){
                   result.plus(ingredient.toString())
               }
           }
       }
        return result
    }


    //Get PublicRecipeFrom Repo and set it as the current Recipe
    fun setRecipeByID(id:Int?){
    if(id == null){
        return
    }
    this.id = id

   var recipe = repo.getPublicRecipe(id)

    if(recipe.value == null){
        return
    }
        image = MutableLiveData(recipe.value!!.imgUrl)
        title = MutableLiveData(recipe.value!!.title)
        preparationDescription = MutableLiveData(recipe.value!!.preparation)
        ingredientChapter = MutableLiveData(recipe.value!!.ingredientChapter)
        tagsList = MutableLiveData(recipe.value!!.tags)
        recipeCookTime =MutableLiveData(recipe.value!!.cookingTime)
        recipePrepTime = MutableLiveData(recipe.value!!.preparationTime)
        creationTime = MutableLiveData(recipe.value!!.creationTimeStamp)
    }

//Wunschkriterium
    fun addToFavourites() {
        //repo.addToFacourites(id)
    }


}