package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.Context
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.*
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.launch
import java.util.*

class CreateRecipeViewmodel(privateRepository: PrivateRecipeRepository,
                            publicRepository: PublicRecipeRepository,
                            userRepository: UserRepository) : ViewModel() {

    var privateRepo = privateRepository
    var publicRepo = publicRepository
    var userRepo = userRepository




    var recipe= MutableLiveData<PrivateRecipe>()

    var recipeID : Int = 0

    var id  = MutableLiveData<Int>(0)
    var title = MutableLiveData<String>("")
    var ingredients = MutableLiveData<String>("")

    var tagCheckBoxVegan: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxVegetarian: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSavoury: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSweet: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSalty: LiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxCheap: LiveData<Boolean> = MutableLiveData(false)

    var preparation = MutableLiveData<String>("")
    var imgUrl  = MutableLiveData<String>("")
    var cookingTime = MutableLiveData<Int>(0)
    var prepTime = MutableLiveData<Int>(0)
    var creationTimeStamp = Date(System.currentTimeMillis())
    var portions = MutableLiveData<Int>(0)
    var publishedID : Int  = 0



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



    //Checkbox if the users whishes to publish his recipe
    var tagCheckBoxPublish: MutableLiveData<Boolean> = MutableLiveData(false)

    /**
     * Gets the Recipe from the Repository and sets the attributes to the livedata objects.
     * @param id The id for the corresponding recipe
     */
    fun setRecipeByID(id: Int) {
        recipeID = id
        if(id != 0) {
            recipe = privateRepo.getPrivateRecipe(recipeID) as MutableLiveData<PrivateRecipe>
        }



}

    /**
     * Stores the recipe in the local Database. If the publish checkbox is activated the method
     * calls the convertToPublicRecipe Method from the local Recipe Repository.
     *
     * @param context is required for the Toast message to notify the user, that the recipe
     * will be published
     */
    fun saveRecipe(context: Context) {
        Log.i("CreateRecipeViewmodel", "funktion save recipe wird aufgerufen")

        // save to room database or update if already exists in room database
        // if recipe ID = 0, it's a new recipe, else update the existing one
       var newPrivateRecipe =
           PrivateRecipe(recipeID, title.value!!,ingredients.value!!,
               getCheckedTags(),preparation.value!!,imgUrl.value!!, cookingTime.value!!, prepTime.value!!, creationTimeStamp, portions.value!!, publishedID)
        //Coroutine
        viewModelScope.launch {
            try {
                privateRepo.insertPrivateRecipe(newPrivateRecipe)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
                Toast.makeText(context, _errorLiveDataString.value, Toast.LENGTH_SHORT).show()
            }
        }
        //if (this.tagCheckBoxPublish.value == true) {
        //TODO obige Abfrage funktioniet nicht. fixen


            Toast.makeText(context, "Rezept wird veröffentlicht", Toast.LENGTH_SHORT).show()
            //TODO dieser Text wird überdeckt von letztem Toast, in Snackbar schreiben.


            //TODO muss anscheinend seit neuestem ein Feld "User übergeben"
            //Man muss da Zugriff auf den Benutzer haben,
            // und wenn keiner angemeldet ist soll man ja auch nicht publishen können

            val user = User("Test")
           //val convertedPublicRecipe = newPrivateRecipe.convertToPublicRepipe(AuthentificationImpl.getUserId())

            val convertedPublicRecipe = PublicRecipe(title="Test", imgUrl = newPrivateRecipe.imgUrl)
            Log.i("CreateRecipeViewmodel", "bin am veröffentlichen des Rezepts")
            //Coroutine
            viewModelScope.launch {
                try {
                   publicRepo.publishRecipe(convertedPublicRecipe)
                } catch (error: Error) {
                    _errorLiveDataString.value = error.message
                    Toast.makeText(context, _errorLiveDataString.value, Toast.LENGTH_SHORT).show()
                }
            }
        //}
    }

    fun getCheckedTags():List<String>{
        var list = mutableListOf<String>()
        list.add("vegan")
        list.add("vegetarisch")

        return list
    }

    fun setTags(list: List<String>){
        if(list.contains("vegan")){
           tagCheckBoxVegan = MutableLiveData(true)

        }
        if(list.contains("vegetarisch")){
            tagCheckBoxVegetarian = MutableLiveData(true)
        }
        if(list.contains("günstig")){
            tagCheckBoxCheap = MutableLiveData(true)
        }
        if(list.contains("herzhaft")){
            tagCheckBoxSavoury = MutableLiveData(true)
        }
        if(list.contains("süß")){
          tagCheckBoxSweet= MutableLiveData(true)
        }
        if(list.contains("salzig")){
            tagCheckBoxSalty= MutableLiveData(true)
        }
    }



}
