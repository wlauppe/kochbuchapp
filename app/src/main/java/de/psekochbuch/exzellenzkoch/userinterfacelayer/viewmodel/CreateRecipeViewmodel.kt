package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch
import java.util.*

class CreateRecipeViewmodel(privateRepository: PrivateRecipeRepository,
                            publicRepository: PublicRecipeRepository) : ViewModel() {

    var privateRepo = privateRepository
    var publicRepo = publicRepository
    var recipe: LiveData<PrivateRecipe> = MutableLiveData(PrivateRecipe( 0,"","", emptyList(), "", "", 0,0,
        Date(System.currentTimeMillis()),0))
    var recipeID = 0

    /**
    * This variable is private because we don't want to expose MutableLiveData
    * MutableLiveData allows anyone to set a value, and MainViewModel is the only
    * class that should be setting values.
    */

    private val _errorLiveDataString = MutableLiveData<String?>()
    /**
     * Request a snackbar to display a string.
     */
    val errorLiveDataString: LiveData<String?>
        get() = _errorLiveDataString


    //Checkboxes for the recipe tags
    var tagCheckBoxVegan: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxVegetarian: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxHearty: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSweet: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSalty: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxCheap: MutableLiveData<Boolean> = MutableLiveData(false)

    //Checkbox if the users whishes to publish his recipe
    var tagCheckBoxPublish: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * Gets the Recipe from the Repository and sets the attributes to the livedata objects.
     * @param id The id for the corresponding recipe
     */
    fun setRecipeByID(id: Int) {

        Log.i("", "setRecByID")

        // var  recipe = repo.getPrivateRecipe(id)

        recipeID = id
        if(id != 0) {
            recipe = privateRepo.getPrivateRecipe(recipeID)
        }


        //set the checkboxes with the set tags
        if (recipe.value?.tags?.contains("vegan")!!) {
            this.tagCheckBoxVegan.value = true
        }
        if (recipe.value!!.tags.contains("vegetarisch")) {
            this.tagCheckBoxVegetarian.value = true
        }
        if (recipe.value!!.tags.contains("günstig")) {
            this.tagCheckBoxCheap.value = true
        }
        if (recipe.value!!.tags.contains("herzhaft")) {
            this.tagCheckBoxHearty.value = true
        }
        if (recipe.value!!.tags.contains("süß")) {
            this.tagCheckBoxSweet.value = true
        }
        if (recipe.value!!.tags.contains("salzig")) {
            this.tagCheckBoxSalty.value = true
        }
    }

    /**
     * Stores the recipe in the Database. If the publish checkbox is activated the method
     * calls the convertToPublicRecipe Method.
     *
     */
    fun saveRecipe() {

        var newRecipe:PrivateRecipe
        // save to room database or update if already exists in room database
        // has to know if recipe is new or already existing -> boolean?
        if ((this.recipeID != 0)) {
            newRecipe = PrivateRecipe(
                recipe.value!!.recipeId,
                this.recipe.value?.title!!, this.recipe.value!!.ingredientsText, getCheckedTags(), this.recipe.value!!.preparation, this.recipe.value!!.imgUrl, recipe.value!!.preparationTime,
                recipe.value!!.preparationTime, Date(System.currentTimeMillis()), portions = this.recipe.value!!.portions)
            //Rezept existiert schon
        } else {
            newRecipe = PrivateRecipe(0,
                this.recipe.value?.title!!, this.recipe.value!!.ingredientsText, getCheckedTags(), this.recipe.value!!.preparation, this.recipe.value!!.imgUrl, recipe.value!!.preparationTime,
                recipe.value!!.preparationTime, Date(System.currentTimeMillis()), portions = this.recipe.value!!.portions)

        }

        //Coroutine
        viewModelScope.launch {
            try {
                privateRepo.insertPrivateRecipe(newRecipe)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
        if (this.tagCheckBoxPublish.value!!) {
            //TODO muss anscheinend seit neuestem ein Feld "User übergeben"
            //Man muss da Zugriff auf den Benutzer haben,
            // und wenn keiner angemeldet ist soll man ja auch nicht publishem können

            val user = User("Todoimplementieren")
            val convertedPublicRecipe = newRecipe.convertToPublicRepipe(user)
            //Coroutine
            viewModelScope.launch {
                try {
                    publicRepo.publishRecipe(convertedPublicRecipe)
                } catch (error: Error) {
                    _errorLiveDataString.value = error.message
                }
            }
        }
    }

    /**
     * Transforms the checkboxes which are checked to strings to store them as taglist
     * @return Tag list List<String>
     */
    fun getCheckedTags(): List<String> {
        val result = mutableListOf<String>()

        if (this.tagCheckBoxVegan.value!!) {
            result.add("vegan")
        }
        if (this.tagCheckBoxCheap.value!!) {
            result.add("günstig")
        }
        if (this.tagCheckBoxHearty.value!!) {
            result.add("herzhaft")
        }
        if (this.tagCheckBoxSalty.value!!) {
            result.add("salzig")
        }
        if (this.tagCheckBoxSweet.value!!) {
            result.add("süß")
        }
        if (this.tagCheckBoxVegetarian.value!!) {
            result.add("vegetarisch")
        }
        return result
    }

    /**
     * converts a copy of the current private recipe to a public recipe. If all the nesessary
     * attributes are fullfilled the public recipe is created and uploaded to the server database.
     */
    fun convertToPublicRecipe() {

    }

    /**
     * starts an intent and gets back the path to the pic.
     * @return the locale path to the image
     */
    fun getImage():String{
    return ""
    }


}