package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import org.junit.Assert.*
import org.junit.Test

class PrivateRecipeRepositoryImpTest{
    @Test
    fun test(){


        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext<Application>())

        repo.insert(PrivateRecipeDB(3,"titl","sdlfjk",1,2,3,4,"dk"))

        Thread.sleep(1000)

        val recipe = repo.get(3)

        Thread.sleep(5000)

        assertEquals(recipe.value!!.preparation,"sdlfjk")

    }
}