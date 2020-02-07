package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import java.security.Timestamp
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class RecipeDisplayViewmodel : ViewModel() {

    var repo = PublicRecipeRepositoryImp()


    //Das Fragment wird nur aufgerufen wenn ein Rezept ausgewählt wird. Daher nicht lateinit

    var recipe :PublicRecipe  = PublicRecipeFakeRepositoryImp().getPublicRecipes().value!![2]



     var image: LiveData<String?> = MutableLiveData(recipe.imgUrl)
     var title: LiveData<String> = MutableLiveData(recipe.title)
     var preparationDescription: LiveData<String> = MutableLiveData(recipe.preparation)
     var ingredientChapter= MutableLiveData(recipe.ingredientChapter)
      var tagsList: LiveData<List<String>> = MutableLiveData(recipe.tags)
      var recipeCookTime: LiveData<Int>  =MutableLiveData(recipe.cookingTime)
      var recipePrepTime: LiveData<Int> = MutableLiveData(recipe.preparationTime)
      var creationTime: LiveData<Date> = MutableLiveData(recipe.creationTimeStamp)

    var creationDate = "Erstellungsdatum: 2020"
      //var rating: LiveData<Double> = MutableLiveData(recipe.rating)


    var tagString :String = tagsList.toString()
    var ingredients = getIngredientsStrings()

    fun getIngredientsStrings():String{
        var result= ""
        for(ingredient in recipe.ingredientChapter){
            for(ingredient in ingredient.ingredients){
                result.plus(ingredient.toString())
            }
        }
        return result
    }

//Dummy MEthode
    fun setRecipeByID(id:Int?){
//Get PublicRecipeFrom Repo and set it as the current Recipe


    if(id == 1){
        this.recipe =  PublicRecipeFakeRepositoryImp().getPublicRecipes().value!![0]
        image = MutableLiveData(recipe.imgUrl)
        title = MutableLiveData(recipe.title)
        preparationDescription = MutableLiveData(recipe.preparation)
        ingredientChapter = MutableLiveData(recipe.ingredientChapter)
        tagsList = MutableLiveData(recipe.tags)
        recipeCookTime =MutableLiveData(recipe.cookingTime)
        recipePrepTime = MutableLiveData(recipe.preparationTime)
        creationTime = MutableLiveData(recipe.creationTimeStamp)
    }
    if(id == 2){
        this.recipe = PublicRecipeFakeRepositoryImp().getPublicRecipes().value!![1]
        image = MutableLiveData(recipe.imgUrl)
        title = MutableLiveData(recipe.title)
        preparationDescription = MutableLiveData(recipe.preparation)
        ingredientChapter = MutableLiveData(recipe.ingredientChapter)
        tagsList = MutableLiveData(recipe.tags)
        recipeCookTime =MutableLiveData(recipe.cookingTime)
        recipePrepTime = MutableLiveData(recipe.preparationTime)
        creationTime = MutableLiveData(recipe.creationTimeStamp)
    }
    if(id == 3){
        this.recipe = PublicRecipeFakeRepositoryImp().getPublicRecipes().value!![2]
        image = MutableLiveData(recipe.imgUrl)
        title = MutableLiveData(recipe.title)
        preparationDescription = MutableLiveData(recipe.preparation)
        ingredientChapter = MutableLiveData(recipe.ingredientChapter)
        tagsList = MutableLiveData(recipe.tags)
        recipeCookTime =MutableLiveData(recipe.cookingTime)
        recipePrepTime = MutableLiveData(recipe.preparationTime)
        creationTime = MutableLiveData(recipe.creationTimeStamp)
    }
    if(id == 4){
        this.recipe = PublicRecipeFakeRepositoryImp().getPublicRecipes().value!![3]
        image = MutableLiveData(recipe.imgUrl)
        title = MutableLiveData(recipe.title)
        preparationDescription = MutableLiveData(recipe.preparation)
        ingredientChapter = MutableLiveData(recipe.ingredientChapter)
        tagsList = MutableLiveData(recipe.tags)
        recipeCookTime =MutableLiveData(recipe.cookingTime)
        recipePrepTime = MutableLiveData(recipe.preparationTime)
        creationTime = MutableLiveData(recipe.creationTimeStamp)
    }





    }


    fun addToFavourites() {
        // TODO
    }

    fun ingredientChapterToString() {
        // TODO
    }

}