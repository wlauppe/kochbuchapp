package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class DisplaySearchListViewmodel : ViewModel() {

    var repo = PublicRecipeRepositoryImp()

    var fakreRepo: PublicRecipeFakeRepository = PublicRecipeFakeRepository()



    fun getPublicRecipes(title:String, ingredients:String, tags:String){

    }



    //Dummy Data
    var first  = "Piroggen"
    var second = "Pommes"
    var third = "Käse"
    var fourth = "Döner"
    var list = listOf<String>(first,second,third,fourth)

   val items: MutableLiveData<List<String>> = MutableLiveData(list)


}