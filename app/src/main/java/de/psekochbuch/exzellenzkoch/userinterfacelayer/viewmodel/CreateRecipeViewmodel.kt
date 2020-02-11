package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import kotlinx.coroutines.launch
import java.util.*

class CreateRecipeViewmodel(repository: PrivateRecipeRepository) : ViewModel() {
    var repo = repository
    var recipe: LiveData<PrivateRecipe> = MutableLiveData()
    var recipeID = 0

    /*
 * This variable is private because we don't want to expose MutableLiveData
 *
 * MutableLiveData allows anyone to set a value, and MainViewModel is the only
 * class that should be setting values.
 */

    private val _errorLiveDataString = MutableLiveData<String?>()
    /**
     * Request a snackbar to display a string.
     */
    val errorLiveDataString: LiveData<String?>
        get() = _errorLiveDataString


    //Current title of the Recipe

    var recipeTitle: MutableLiveData<String> = MutableLiveData("")
    var imageUrl: String = ""
    //Current Preparation Time of the Reci
    var preparationTime: MutableLiveData<String> = MutableLiveData("0")

    //current cookingTime for the Recipe
    var cookingTime: MutableLiveData<String> = MutableLiveData("0")

    //current tags for the Recipe
    var tagList: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    //current preparation description for the recipe
    var preparationDescription: MutableLiveData<String> = MutableLiveData("")
    //current ingredients for the recipe as String
    var ingredients: MutableLiveData<String> = MutableLiveData("")
    //current number of portions for the recipe
    var portions: MutableLiveData<Int> = MutableLiveData(0)


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
        // var  recipe = repo.getPrivateRecipe(id)
        recipeID = id
        var recipe = repo.getPrivateRecipe(recipeID)
        if(recipe.value == null){
            return
        }
        var tags = recipe.value!!.tags
        // The livedata attributes are set with the recipe contents
        this.imageUrl = recipe.value!!.imgUrl
        this.recipeTitle = MutableLiveData(recipe.value!!.title)
        preparationDescription = MutableLiveData(recipe.value!!.preparation)
        this.ingredients = MutableLiveData(recipe.value!!.ingredientsText)
        this.tagList = MutableLiveData(recipe.value!!.tags)
        this.cookingTime = MutableLiveData(recipe.value!!.cookingTime.toString())

        this.preparationTime = MutableLiveData(recipe.value!!.preparationTime.toString())

        this.portions = MutableLiveData(recipe.value!!.portions)
        //set the checkboxes with the settet tags
        if (tags.contains("vegan")) {
            this.tagCheckBoxVegan.value = true
        }
        if (tags.contains("vegetarian")) {
            this.tagCheckBoxVegetarian.value = true
        }
        if (tags.contains("günstig")) {
            this.tagCheckBoxCheap.value = true
        }
        if (tags.contains("herzhaft")) {
            this.tagCheckBoxHearty.value = true
        }
        if (tags.contains("süß")) {
            this.tagCheckBoxSweet.value = true
        }
        if (tags.contains("salzig")) {
            this.tagCheckBoxSalty.value = true
        }
    }

    /**
     * Stores the recipe in the Database. If the publish checkbox is activated the method
     * calls the convertToPublicRecipe Method.
     *
     */
    fun saveRecipe() {


            var newRecipe = PrivateRecipe(0, this.recipeTitle.value!!, this.ingredients.value!!, getCheckedTags(), this.preparationDescription.value!!, this.imageUrl, Integer.parseInt(this.cookingTime.value!!),Integer.parseInt(this.preparationTime.value!!), Date(System.currentTimeMillis()), portions = this.portions.value!!)
        //Coroutine
        viewModelScope.launch {
            try {
                repo.insertPrivateRecipe(newRecipe)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }

        if (this.tagCheckBoxPublish.value!!) {
            convertToPublicRecipe()
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
     * converts a copie of the current private recipe to a public recipe. If all the nesessary
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