package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.repository.PrivateRecipeRepositoryImplementation
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe


class RecipeListViewmodel: ViewModel() {

    var mrepo = PrivateRecipeRepositoryImplementation()
   // var names: MutableLiveData<List<String>>
   // var recipes: MutableLiveData<List<PrivateRecipe>> = mrepo.getPrivateRecipes() as MutableLiveData<List<PrivateRecipe>>
var recipes: MutableLiveData<List<PrivateRecipe>> = MutableLiveData()




    fun getNamesFromRecipes(liveData:LiveData<List<PrivateRecipe>>){
        //TODO

    }

     fun deleteRecipe(id: Int?) {
         //TODO

    }


}