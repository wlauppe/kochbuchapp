package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.TagFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import kotlinx.coroutines.launch
import java.util.*

class CreateRecipeViewmodel(repository: PrivateRecipeRepository) : ViewModel() {
    var repo = repository
    var recipe: MutableLiveData<PrivateRecipe?> = MutableLiveData()
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
    var recipeTitle: LiveData<String> = MutableLiveData("")

    var imageUrl: LiveData<String> = MutableLiveData("")
    //Current Preparation Time of the Reci
    var preparationTime: LiveData<Int> = MutableLiveData(0)

    var cookingTime: LiveData<Int> = MutableLiveData(0)

    var tagList: LiveData<List<String>> = MutableLiveData(emptyList())

    var preparationDescription: LiveData<String> = MutableLiveData("")

    var ingredients: LiveData<String> = MutableLiveData("")

    var portions: LiveData<Int> = MutableLiveData(0)

    //var creationTimeStam = Date()


    var tagCheckBoxVegan: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxVegetarian: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxHearty: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSweet: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSalty: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxCheap: MutableLiveData<Boolean> = MutableLiveData(false)

    var tagCheckBoxPublish: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setRecipeByID(id: Int) {
        // var  recipe = repo.getPrivateRecipe(id)
        recipeID = id

        //Dummy
        var tags = listOf<String>("eins", "zwei")

        var recipe = MutableLiveData(
            PrivateRecipe(
                0,
                "Tomaten",
                "zutaten, Karotten",
                tags,
                "zubereitungs",
                "",
                4,
                2,
                Date(),
                2
            )
        )
        var tagRepo = TagFakeRepositoryImp()


        this.imageUrl = MutableLiveData(recipe.value!!.imgUrl)
        this.recipeTitle = MutableLiveData(recipe.value!!.title)
        preparationDescription = MutableLiveData(recipe.value!!.preparation)
        this.ingredients = MutableLiveData(recipe.value!!.ingredientsText)
        this.tagList = MutableLiveData(recipe.value!!.tags)
        this.cookingTime = MutableLiveData(recipe.value!!.cookingTime)
        this.preparationTime = MutableLiveData(recipe.value!!.preparationTime)
        this.portions = MutableLiveData(recipe.value!!.portions)

        if (tags.contains("vegan")) {
            this.tagCheckBoxVegan.value = true
        }
        if (tags.contains("vegetarian")) {
            this.tagCheckBoxVegetarian.value = true
        }
        /* var recipetemp = repo.getPrivateRecipe(id).value

         this.recipe = MutableLiveData(recipetemp)

         this.preparationTime = MutableLiveData(recipe.value!!.preparationTime)
         this.cookingTime = MutableLiveData(recipe.value!!.cookingTime)
         this.preparationDescription = MutableLiveData(recipe.value!!.preparation)
         this.ingredients = MutableLiveData(recipe.value!!.ingredientsText)
         tagsSet()


     }

     fun tagsSet(){
         var tags = recipe.value!!.tags
         if(tags.contains("vegan")){
             this.tagCheckBoxVegan.value!!.isChecked = true
         }
         //...

         */
    }

    fun saveRecipe() {

        var title: String = this.recipeTitle.value!!
        var ingredientsText: String = this.ingredients.value!!
        var tags: List<String> = getCheckedTags()
        var preparation: String = this.preparationDescription.value!!
        var imgUrl: String = this.imageUrl.value!!
        var cookingTime: Int = this.cookingTime.value!!
        var preparationTime: Int = this.preparationTime.value!!
        var creationTimeStamp: Date = Date()
        var portions: Int = this.portions.value!!

        if (this.tagCheckBoxPublish.value!!) {
            convertToPublicRecipe()
        }

        /*
        if (repo.getPrivateRecipe(recipeID).value == null) {

            var newRecipe = PrivateRecipe(
                0,
                title,
                ingredientsText,
                tags,
                preparation,
                imgUrl,
                cookingTime,
                preparationTime,
                Date(),
                portions
            )
            //coroutine
            viewModelScope.launch {
                try {
                    repo.insertPrivateRecipe(newRecipe)
                } catch (error: Error) {
                    _errorLiveDataString.value = error.message
                }
            }
        }
         */

    }

    fun getCheckedTags(): List<String> {
        var result = mutableListOf<String>()

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

    fun convertToPublicRecipe() {

    }


}