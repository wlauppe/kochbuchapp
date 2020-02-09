package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import java.security.Timestamp
import java.util.*

class PrivateRecipeFakeRepositoryImp : PrivateRecipeRepository {

    private val creationTimestamp: Date = Date()
     var rec:PrivateRecipe = PrivateRecipe(0, "Kartoffelklöße",
        "3 große Kartoffeln", listOf("herzhaft, deftig"), "kochen",
        "file:///android_asset/exampleimages/quiche.png", 20, 24, creationTimestamp,
        5)


    private val rec2 = rec

    private val list = listOf(rec, rec2)
    private val ld : MutableLiveData<List<PrivateRecipe>> = MutableLiveData(list)

    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        return ld
    }

    override fun getPrivateRecipe(id: Int): LiveData<PrivateRecipe> {
        TODO()
    }

    override suspend fun deletePrivateRecipe(id: Int) {
       TODO()
    }

    override suspend fun updatePrivateRecipe(id: Int) {
        TODO()
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecipe(id: String): LiveData<PrivateRecipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PrivateRecipeRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PrivateRecipeFakeRepositoryImp().also { instance = it }
        }
    }
}
