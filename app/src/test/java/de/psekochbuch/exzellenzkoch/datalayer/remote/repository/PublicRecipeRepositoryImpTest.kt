package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class PublicRecipeRepositoryImpTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun getPublicRecipe() {

            val repo = PublicRecipeRepositoryImp.getInstance()
            val lrecipe=repo.getPublicRecipe(1)
            lrecipe.observeForever{}
            Thread.sleep(1000)
            assertEquals(lrecipe.value!!.recipeId,"1")

        }
    }
