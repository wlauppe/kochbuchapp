package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.repository.PrivateRecipeRepositoryImplementation
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe


class RecipeListViewmodel: ViewModel() {

    var mrepo = PrivateRecipeRepositoryImplementation()
    var names: MutableLiveData<List<String>> ? = null
    var recipes: MutableList<PrivateRecipe>? = mrepo.getPrivateRecipes().value as MutableList<PrivateRecipe>?

    var buttons:List<Button>? = null



    fun getNamesFromRecipes(liveData:LiveData<List<PrivateRecipe>>){

    }

     fun deleteRecipe(id: Int?) {
         //TODOD

    }


}