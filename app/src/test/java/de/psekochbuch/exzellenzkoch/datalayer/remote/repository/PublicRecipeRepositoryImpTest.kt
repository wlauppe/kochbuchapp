package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import org.junit.Test

import org.junit.Assert.*

class PublicRecipeRepositoryImpTest {

    @Test
    fun getPublicRecipe() {

            val repo = PublicRecipeRepositoryImp()
            val lrecipe=repo.getPublicRecipe(1)
            lrecipe.observeForever{}
            //assertEquals(lrecipe.value!!.recipeId,"1")

        }



    }
