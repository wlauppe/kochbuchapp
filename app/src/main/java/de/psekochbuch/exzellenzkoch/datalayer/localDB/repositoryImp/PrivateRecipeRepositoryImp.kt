package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeTagDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import java.lang.IllegalArgumentException
import java.util.*

class PrivateRecipeRepositoryImp(application: Application?): PrivateRecipeRepository {
    private val privateRecipeDao: PrivateRecipeDao? = DB.getDatabase(application!!)?.privateRecipeDao();
    private val privateRecipeTagDao: PrivateRecipeTagDao? = DB.getDatabase(application!!)?.privateRecipeTagDao();

    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        val recipes = transformListPrivateRecipeDBToListPrivateRecipeDB(privateRecipeDao?.getAll()!!)
        val liveData = MutableLiveData<List<PrivateRecipe>>()
        liveData.postValue(recipes)
        return liveData
    }


    override fun getPrivateRecipe(id: Int): LiveData<PrivateRecipe> {
        val recipe = privateRecipeDao?.getRecipe(id.toLong())
        val liveData = MutableLiveData<PrivateRecipe>()
        if (recipe != null){
            liveData.postValue(transformPrivateRecipeDBToPrivateRecipe(recipe))
        }
        return liveData
    }

    override suspend fun deletePrivateRecipe(id: Int) {
        privateRecipeDao?.deleteRecipe(id.toLong())
        privateRecipeTagDao?.deleteTagsFromRecipe(id.toLong())
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        DB.databaseWriteExecutor.execute{
            val recipeId = privateRecipeDao?.insert(transformPrivateRecipeToPrivateRecipeDB(privateRecipe))!!.toLong()

            for (tag:String in privateRecipe.tags){
                privateRecipeTagDao?.insert(PrivateRecipeTagDB(0,recipeId, tag))
            }
        }
    }

    fun transformPrivateRecipeDBToPrivateRecipe(recipe:PrivateRecipeDB):PrivateRecipe{
        return PrivateRecipe(recipe.id.toInt(), recipe.title!!,recipe.ingredientsText!!,privateRecipeTagDao?.getTagsFromRecipe(recipe.id)!!.map{tag -> tag.tag},recipe.preparationDescription!!,"wiesoURL?",recipe.cookingTime!!,recipe.preparationTime!!, Date(recipe.creationDate!!),recipe.portions!!)
    }

    fun transformPrivateRecipeToPrivateRecipeDB(recipe:PrivateRecipe):PrivateRecipeDB{
        return PrivateRecipeDB(recipe.recipeId.toLong(),recipe.title,recipe.preparation,recipe.cookingTime,recipe.preparationTime,recipe.creationTimeStamp.time,recipe.portions,recipe.ingredientsText)
    }

    fun transformListPrivateRecipeDBToListPrivateRecipeDB(recipes:List<PrivateRecipeDB>):List<PrivateRecipe>{
        return recipes.map(::transformPrivateRecipeDBToPrivateRecipe)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PrivateRecipeRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PrivateRecipeRepositoryImp(application = Application()).also { instance = it }
            }
    }
}