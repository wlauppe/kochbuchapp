package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

/**
 * The CreateRecipeViewModel handles the information for the CreateRecipeFragment.
 * @param privateRepository: The repository through which the private recipes are managed.
 * @param publicRepository: The repositroy through which the public recipes are managed.
 * @param userRepository: The repository through which the users are handled
 */
class CreateRecipeViewmodel(privateRepository: PrivateRecipeRepository,
                            publicRepository: PublicRecipeRepository,
                            userRepository: UserRepository) : ViewModel() {
    var Tag = "CreateRecipeVM"

    var privateRepo = privateRepository
    var publicRepo = publicRepository
    var userRepo = userRepository


    var recipe= MutableLiveData<PrivateRecipe>()

    var recipeID : Int = 0

    var id : MutableLiveData<Int> = MutableLiveData(0)
    var title:MutableLiveData<String>  = MutableLiveData<String>("")
    var ingredients :MutableLiveData<String> = MutableLiveData<String>("")

    var tagCheckBoxVegan: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxVegetarian: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSavoury: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSweet: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxSalty: MutableLiveData<Boolean> = MutableLiveData(false)
    var tagCheckBoxCheap: MutableLiveData<Boolean> = MutableLiveData(false)

    var preparation : MutableLiveData<String> = MutableLiveData<String>("")
    var imgUrl  = MutableLiveData<String>("")


    var cookingTime = MutableLiveData<String>("0")
    var prepTime = MutableLiveData<String>("0")


    var creationTimeStamp = Date(System.currentTimeMillis())
    var portions = MutableLiveData<Int>(0)
    var publishedID : Int  = 0

    //Checkbox if the users whishes to publish his recipe
    var tagCheckBoxPublish: MutableLiveData<Boolean> = MutableLiveData(false)



    /**
    * This variable is private because we don't want to expose MutableLiveData
    * MutableLiveData allows anyone to set a value, and MainViewModel is the only
    * class that should be setting values.
    */
    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    /**
     * If this is true, immediately `show()` a toast and call `doneShowingSnackbar()`.
     */
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent


    private val _snackbarMessage = MutableLiveData<String?>()
    /**
     * Request a snackbar to display a string.
     */
    val snackbarMessage: LiveData<String?>
        get() = _snackbarMessage

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }


    //Checkboxes for the recipe tags


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
     *
     *
     */

    fun publishRecipe() {
        val loggedIn = AuthentificationImpl.isLogIn()
        if(loggedIn) {
            _snackbarMessage.value = "Beim Speichern wird Rezept als öffentlich verfügbar gemacht"
        }
        else {
            _snackbarMessage.value = "Fehler: Sie sind nicht mit einem Account eingeloggt."
        }
        _showSnackbarEvent.value = true
    }

    fun dontPublishRecipe() {
        _snackbarMessage.value = "Beim Speichern wird ein öffentlich verfügbares Rezept gelöscht"
        _showSnackbarEvent.value = true
    }

    fun saveRecipe(context: Context) {
        if(tagCheckBoxPublish.value == true) {
            _snackbarMessage.value = "Rezept wird gespeichert und veröffentlicht"
        }
        else {
            _snackbarMessage.value = "Rezept wird gespeichert"
        }
        _showSnackbarEvent.value = true

        Log.i("CreateRecipeViewmodel", "funktion save recipe wird aufgerufen")

        // save to room database or update if already exists in room database
        // if recipe ID = 0, it's a new recipe, else update the existing one
        var resultTags = getCheckedTags()
        if (imgUrl.value.equals("") ) {
           imgUrl.value = recipe.value?.imgUrl
        }
        if(imgUrl.value.isNullOrEmpty()){
            imgUrl.value =  "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        }
       var newPrivateRecipe =
           PrivateRecipe(recipeID, title.value!!,ingredients.value!!,
               resultTags,preparation.value!!,imgUrl.value!!, Integer.parseInt(cookingTime.value!!), Integer.parseInt(prepTime.value!!), creationTimeStamp, portions.value!!, publishedID)

            //Coroutine Saving in Room Database

         //Man muss da Zugriff auf den Benutzer haben,
         // und wenn keiner angemeldet ist soll man ja auch nicht publishen können
        if(this.tagCheckBoxPublish.value == true && AuthentificationImpl.isLogIn()){
            val userId = AuthentificationImpl.getUserId()
            val user = User(userId)
            //val _user = userRepo.getUser(userId)
           val convertedPublicRecipe = newPrivateRecipe.convertToPublicRepipe(user)
           // val convertedPublicRecipe = PublicRecipe(title="Test", user=user,imgUrl = newPrivateRecipe.imgUrl)
            Log.i("CreateRecipeViewmodel", "bin am veröffentlichen des Rezepts")
            //Coroutine
            viewModelScope.launch {
                try {
                   val newId = publicRepo.publishRecipe(convertedPublicRecipe)

                    //muss jetzt noch mal das private Recipe mit der Id unter der das Rezept gepublished
                    //wurde speichern.

                    var newPrivateRecipe =
                        PrivateRecipe(recipeID, title.value!!,ingredients.value!!,
                            resultTags,preparation.value!!,imgUrl.value!!, Integer.parseInt(cookingTime.value!!), Integer.parseInt(prepTime.value!!), creationTimeStamp, portions.value!!, newId)
                    //Coroutine Saving in Room Database
                            privateRepo.insertPrivateRecipe(newPrivateRecipe)

                } catch (error: Error) {
                    _snackbarMessage.value = error.message
                    Toast.makeText(context, _snackbarMessage.value, Toast.LENGTH_SHORT).show()
                }
                
            }
        } else if (this.tagCheckBoxPublish.value == false){
            viewModelScope.launch {
                try {
                    privateRepo.insertPrivateRecipe(newPrivateRecipe)
                } catch (error: Error) {
                    _snackbarMessage.value = error.message
                    Toast.makeText(context, _snackbarMessage.value, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getCheckedTags():List<String>{
        var list = mutableListOf<String>()

       if(this.tagCheckBoxVegan.value!!){
           list.add("vegan")
           Log.i(Tag, "vegan ist enthalten" )
       }
        if(this.tagCheckBoxVegetarian.value!!){
            list.add("vegetarisch")
            Log.i(Tag, "vegetarisch ist enthalten" )
        }
        if(this.tagCheckBoxCheap.value!!){
            list.add("günstig")
            Log.i(Tag, "günstig ist enthalten" )
        }
        if(this.tagCheckBoxSavoury.value!!){
            list.add("herzhaft")

            Log.i(Tag, "herzhaft ist enthalten" )
        }
        if(this.tagCheckBoxSweet.value!!){
            list.add("sweet")
            Log.i(Tag, "süß ist enthalten" )
        }
        return list.toList()
    }

    fun setTags(tags : List<String>){
        if(tags.contains("vegan")){
           tagCheckBoxVegan.value = true
        }
        if(tags.contains("vegetarisch")){
           tagCheckBoxVegetarian.value = true
        }
        if(tags.contains("salzig")){
            tagCheckBoxSalty.value = true
        }
        if(tags.contains("sweet")){
          tagCheckBoxSweet.value = true
        }
        if(tags.contains("günstig")){
           tagCheckBoxCheap.value = true
        }
        if(tags.contains("herzhaft")){
            tagCheckBoxSavoury.value = true
        }
    }







}
