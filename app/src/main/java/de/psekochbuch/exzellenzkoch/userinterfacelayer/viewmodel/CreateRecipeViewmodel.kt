package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import kotlinx.coroutines.launch
import java.util.*

class CreateRecipeViewmodel(privateRepository: PrivateRecipeRepository,
                            publicRepository: PublicRecipeRepository,
                            userRepository: UserRepository) : ViewModel() {

    var privateRepo = privateRepository
    var publicRepo = publicRepository
    var userRepo = userRepository
    var recipe: LiveData<PrivateRecipe> = MutableLiveData(PrivateRecipe( 0,"","", listOf(""), "", "", 0,0,
        Date(System.currentTimeMillis()),0))
    var recipeID = 0

    //LiveData Attributes for XML

    var title  :MutableLiveData<String> = MutableLiveData("")
    @Bindable
    fun getTitle():String{
        return title.value!!
    }
    fun setTitle(title: String){
        this.title.postValue(title)
    }

    var preparationTime:MutableLiveData<Int>  = MutableLiveData(0)
    @Bindable
    fun getPreparationTime():Int{
        return preparationTime.value!!
    }
    fun setPreparationTime(time:Int){
        preparationTime.postValue(time)
    }

    var cookingTime:MutableLiveData<Int>  = MutableLiveData(0)
    @Bindable
    fun getCookingTime():Int{
        return cookingTime.value!!
    }
    fun setCookingTime(time:Int){
        cookingTime.postValue(time)
    }

    var ingredients:MutableLiveData<String>  = MutableLiveData("")
    @Bindable
    fun getIngredients():String{
        return ingredients.value!!
    }
    fun setIngredients(ingredients:String){
        this.ingredients.postValue(ingredients)
    }

    var description:MutableLiveData<String>  = MutableLiveData("")
    @Bindable
    fun getDescription():String{
        return description.value!!
    }
    fun setDescription(description: String){
        this.description.postValue(description)
    }

    var portions:MutableLiveData<Int>  = MutableLiveData(0)
    @Bindable
    fun getPortions():Int{
        return portions.value!!
    }
    fun setPortions(portions:Int){
        this.portions.postValue(portions)
    }

    var imgUrl:MutableLiveData<String>  = MutableLiveData("")

    var creationDate = recipe.value!!.creationTimeStamp.toString()


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
    var tagCheckBoxVegan: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxVegetarian: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxHearty: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSweet: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSalty: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxCheap: LiveData<Boolean> = MutableLiveData(false)



    //Checkbox if the users whishes to publish his recipe
    var tagCheckBoxPublish: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * Gets the Recipe from the Repository and sets the attributes to the livedata objects.
     * @param id The id for the corresponding recipe
     */
    fun setRecipeByID(id: Int) {
        Log.i("", "setRecByID")
        recipeID = id
        if(id != 0) {
            recipe = privateRepo.getPrivateRecipe(recipeID)
        }
          tagCheckBoxVegan =
           Transformations.map(recipe) { recipe -> recipe.tags.contains("vegan")}
          tagCheckBoxVegetarian =
            Transformations.map(recipe) { recipe -> recipe.tags.contains("vegetarisch") }
          tagCheckBoxCheap =
            Transformations.map(recipe) { recipe -> recipe.tags.contains("günstig")}
          tagCheckBoxHearty =
            Transformations.map(recipe) { recipe -> recipe.tags.contains("herzhaft")}
          tagCheckBoxSweet =
            Transformations.map(recipe) { recipe -> recipe.tags.contains("süß")}
          tagCheckBoxSalty =
            Transformations.map(recipe) { recipe -> recipe.tags.contains("salzig")}


          title.postValue("testwertThomas")
/*
          preparationTime = Transformations.map(recipe){recipe -> recipe.preparationTime}

          cookingTime = Transformations.map(recipe){recipe -> recipe.cookingTime}

          ingredients = Transformations.map(recipe){recipe -> recipe.ingredientsText}

        description = Transformations.map(recipe){recipe -> recipe.preparation}

        imgUrl = Transformations.map(recipe){recipe -> recipe.imgUrl}

        portions = Transformations.map(recipe){recipe -> recipe.portions}
*/
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
                title.value!!, ingredients.value!!, listOf("vegan"), description.value!!, imgUrl.value!!,
                cookingTime.value!!,
                preparationTime.value!!, Date(System.currentTimeMillis()), portions.value!!)
            //Rezept existiert schon
        } else {
            newRecipe = PrivateRecipe(0,
                title.value!!,
                ingredients.value!!, listOf("vegan"),description.value!!,imgUrl.value!!, cookingTime.value!!,
                preparationTime.value!!, Date(System.currentTimeMillis()),portions.value!!)

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
            // und wenn keiner angemeldet ist soll man ja auch nicht publishen können

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

    fun setLiveData(){
         title  = MutableLiveData(recipe.value!!.title)
         creationDate = recipe.value!!.creationTimeStamp.toString()
         preparationTime = MutableLiveData(recipe.value!!.preparationTime)
         cookingTime = MutableLiveData(recipe.value!!.cookingTime)
         ingredients = MutableLiveData(recipe.value!!.ingredientsText)
         description = MutableLiveData(recipe.value!!.preparation)
         imgUrl = MutableLiveData(recipe.value!!.imgUrl)
         portions = MutableLiveData(recipe.value!!.portions)



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