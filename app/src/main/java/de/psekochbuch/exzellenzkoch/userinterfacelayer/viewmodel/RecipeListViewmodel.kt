package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.repository.PrivateRecipeRepositoryImplementation
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe


class RecipeListViewmodel: ViewModel() {

    var mrepo = PrivateRecipeRepositoryImplementation()
    var names: MutableList<String> ? = mutableListOf<String>()

    


    fun getNamesFromRecipes(liveData:LiveData<List<PrivateRecipe>>){

        names = mutableListOf()
        if(liveData.value.isNullOrEmpty()){
            return
        }

        for(privateRecipe in liveData.value!!){
            names!!.add(privateRecipe.title)
        }

    }


}