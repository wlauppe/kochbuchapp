package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
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
        val recipe = transformPrivateRecipeDBToPrivateRecipe(privateRecipeDao?.getRecipe(id.toLong())!!)
        val liveData = MutableLiveData<PrivateRecipe>()
        liveData.postValue(recipe)
        return liveData
    }

    override suspend fun deletePrivateRecipe(id: Int) {
        privateRecipeDao?.deleteRecipe(id.toLong())
        privateRecipeTagDao?.deleteTagsFromRecipe(id.toLong())
    }

    override suspend fun updatePrivateRecipe(privateRecipe: PrivateRecipe) {
       //Was ist denn hiermit gemeint? Falls damit das überschreiben eines rezeptes gemeient ist, das passiet automatisch wenn man das in die insert Methode einfügt, da dies bei collision einfach überschreibet
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        DB.databaseWriteExecutor.execute{privateRecipeDao?.insert(transformPrivateRecipeToPrivateREcipeDB(privateRecipe))}
    }

    override fun getRecipe(id: Int): LiveData<PrivateRecipe> {
        TODO()
    }

    fun transformPrivateRecipeDBToPrivateRecipe(recipe:PrivateRecipeDB):PrivateRecipe{
        //können wir IDs auch als longs abspeichern?
        return PrivateRecipe(recipe.id.toInt(), recipe.title!!,recipe.ingredientsText!!,privateRecipeTagDao?.getTagsFromRecipe(recipe.id)!!.map{tag -> tag.tag},recipe.preparationDescription!!,"wiesoURL?",recipe.cookingTime!!,recipe.preparationTime!!, Date(recipe.creationDate!!),recipe.portions!!)
    }

    fun transformPrivateRecipeToPrivateREcipeDB(recipe:PrivateRecipe):PrivateRecipeDB{
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