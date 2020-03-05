package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * The RecipeListViewmodel handles the information for the RecipeListFragment.
 * @param privateRepository: The repository through which the private recipes are managed.
 * @param publicRepo: The repository through which the public-recipe-related methods
 * are handled.
 */
class RecipeListViewmodel(privateRepository: PrivateRecipeRepository,
                          publicRepo: PublicRecipeRepository) : ViewModel() {

    var privateRepo = privateRepository
    val publicRepo = publicRepo
    var recipes : LiveData<List<PrivateRecipe>> = MutableLiveData(emptyList())
    var tag = "RecipeListVM"

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

    /**
     * Returns the private recipes from the particular user.
     */
    fun getPrivateRecipes() {
        recipes = privateRepo.getPrivateRecipes()
    }

    /**
     * Deletes the recipe from the local database and the server.
     * @param id: the id from the specific user which should be deleted
     */
    fun deleteRecipe(id: Int?) {
        

        if(id !=null) {
            Log.i(tag, "funktion delete recipe wird aufgerufen")

            this.recipes.value?.forEach {if(it.recipeId == id){
                Log.i(tag, "habe privates Rezept in Liste gefunden, kann öffentliches Rezept löschen, had id ${it.publishedRecipeId}")
                if(it.publishedRecipeId != 0){
                    GlobalScope.launch {
                        try {
                            publicRepo.deleteRecipe(it.publishedRecipeId)
                        } catch (error: Error) {
                            _errorLiveDataString.value = error.message
                        }
                    }
                }
            }
            //coroutine
            viewModelScope.launch {
                   //  val recipeLiveData = privateRepo.getPrivateRecipe(id)
                   //  delay(2000L)
                  // publicRepo.deleteRecipe(recipeLiveData.value!!.publishedRecipeId)

                   // recipeLiveData.observeForever { recipe ->
                   //                             runBlocking() { publicRepo.deleteRecipe(recipe.publishedRecipeId) }
                   // }

                try {
                    privateRepo.deletePrivateRecipe(id)
                } catch (error: Error) {
                    _errorLiveDataString.value = error.message
                }
            }

            }
        }
    }
}