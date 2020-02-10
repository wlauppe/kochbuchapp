package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

@Test
fun PublicRecipeRepositoryImpTest(){
    val repo = PublicRecipeRepositoryImp()
    val lrecipe=repo.getPublicRecipe(1)
    lrecipe.observeForever{}
    assertEquals(lrecipe.value!!.recipeId,"1")

}


