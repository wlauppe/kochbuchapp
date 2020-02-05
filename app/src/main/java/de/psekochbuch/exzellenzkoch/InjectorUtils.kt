package de.psekochbuch.exzellenzkoch

import android.content.Context

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp

object InjectorUtils {

    private fun getPublicRecipeRepository(context: Context): PublicRecipeRepository {
        return PublicRecipeFakeRepositoryImp.getInstance()

    }


    //Beispiel für eine Viewmodel Factory
    //TODO auf unsere Situation anpassen:
  /*  fun provideGardenPlantingListViewModelFactory(
        context: Context
    ): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }
   */

}
