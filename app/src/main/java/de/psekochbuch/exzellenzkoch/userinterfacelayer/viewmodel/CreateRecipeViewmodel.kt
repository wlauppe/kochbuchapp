package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.TagFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CreateRecipeViewmodel(repository:PrivateRecipeRepository) : ViewModel() {
    var repo = repository
    var recipe : MutableLiveData<PrivateRecipe?> = MutableLiveData()


    //Current title of the Recipe
    var recipeTitle: MutableLiveData<String> = MutableLiveData()

    //Current Preparation Time of the Reci
    var preparationTime: MutableLiveData<Int> = MutableLiveData()

    var cookingTime: MutableLiveData<Int> = MutableLiveData()

    var preparationDescription: MutableLiveData<String> = MutableLiveData()

    var ingredients: MutableLiveData<String> = MutableLiveData()



    var tagCheckBoxVegan: MutableLiveData<CheckBox> = MutableLiveData()
    var tagCheckBoxVegetarian: MutableLiveData<CheckBox> = MutableLiveData()
    var tagCheckBoxHearty: MutableLiveData<CheckBox> = MutableLiveData()
    var tagCheckBoxSweet: MutableLiveData<CheckBox> = MutableLiveData()
    var tagCheckBoxSalty: MutableLiveData<CheckBox> = MutableLiveData()
    var tagCheckBoxCheap: MutableLiveData<CheckBox> = MutableLiveData()

    var tagCheckBoxPublish: MutableLiveData<CheckBox> = MutableLiveData()

    fun setRecipeByID(id:Int){
        var tagRepo = TagFakeRepositoryImp()
        var taglist = listOf<String>("eins", "zwei")

        this.recipe = MutableLiveData(PrivateRecipe(2, "dummyRezept", "Zutaten: Möhren, Gurke, Brot", taglist, "einfach lang genug kochen", "", 30, 40, Date(), 4))
        //image = MutableLiveData(recipe.imgUrl)
        this.recipeTitle = MutableLiveData(recipe.value!!.title)
        preparationDescription = MutableLiveData(recipe.value!!.preparation)
        this.ingredients = MutableLiveData(recipe.value!!.ingredientsText)
       // tagsList = MutableLiveData(recipe.tags)
        this.cookingTime =MutableLiveData(recipe.value!!.cookingTime)
        this.preparationTime = MutableLiveData(recipe.value!!.preparationTime)
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

    fun saveRecipe(){

    }






}