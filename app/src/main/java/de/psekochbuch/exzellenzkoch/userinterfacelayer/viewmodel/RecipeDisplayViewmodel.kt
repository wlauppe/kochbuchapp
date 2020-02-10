package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch
import java.util.*

class RecipeDisplayViewmodel(repository:PublicRecipeRepository) : ViewModel() {

    var repo = repository


    //Das Fragment wird nur aufgerufen wenn ein Rezept ausgewählt wird. Daher nicht lateinit

    var recipe :PublicRecipe? = null

    private val _errorLiveDataString = MutableLiveData<String?>()
    /**
     * Request a snackbar to display a string.
     */
    val errorLiveDataString: LiveData<String?>
        get() = _errorLiveDataString
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
       var  recipeLiveData: LiveData<PublicRecipe> = MutableLiveData()
        viewModelScope.launch {
            try {
                recipeLiveData = repo.getPublicRecipe(id)
                image = MutableLiveData(recipeLiveData.value!!.imgUrl)
                title = MutableLiveData(recipeLiveData.value!!.title)
                preparationDescription = MutableLiveData(recipeLiveData.value!!.preparation)
                ingredientChapter = MutableLiveData(recipeLiveData.value!!.ingredientChapter)
                tagsList = MutableLiveData(recipeLiveData.value!!.tags)
                recipeCookTime =MutableLiveData(recipeLiveData.value!!.cookingTime)
                recipePrepTime = MutableLiveData(recipeLiveData.value!!.preparationTime)
                creationTime = MutableLiveData(recipeLiveData.value!!.creationTimeStamp)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
    }

//Wunschkriterium
    fun addToFavourites() {
        //repo.addToFacourites(id)
    }


}