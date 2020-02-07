package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository

class CreateRecipeViewmodel(repository:PrivateRecipeRepository) : ViewModel() {


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


}