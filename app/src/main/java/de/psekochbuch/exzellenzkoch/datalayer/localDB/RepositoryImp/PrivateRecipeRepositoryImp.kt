package de.psekochbuch.exzellenzkoch.datalayer.localDB.RepositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import java.util.*

class PrivateRecipeRepositoryImp(application: Application?): PrivateRecipeRepository {
    private val privateRecipeDao: PrivateRecipeDao? = DB.getDatabase(application!!)?.privateRecipeDao();
    private val privateRecipeTagDao: PrivateRecipeTagDao? = DB.getDatabase(application!!)?.privateRecipeTagDao();

    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        return Transformations.map(privateRecipeDao?.getAll()!!,::transformListPrivateRecipeDBToListPrivateRecipeDB)
    }

    override fun getPrivateRecipe(id: Int): LiveData<PrivateRecipe> {
        return Transformations.map(privateRecipeDao?.getRecipe(id.toLong())!!,::transformPrivateRecipeDBToPrivateRecipe)
    }

    override suspend fun deletePrivateRecipe(id: Int) {
        privateRecipeDao?.deleteRecipe(id.toLong())
        privateRecipeTagDao?.deleteTagsFromRecipe(id.toLong())
    }

    override suspend fun updatePrivateRecipe(id: Int) {
       //Was ist denn hiermit gemeint? Falls damit das überschreiben eines rezeptes gemeient ist, das passiet automatisch wenn man das in die insert Methode einfügt, da dies bei collision einfach überschreibet
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        DB.databaseWriteExecutor.execute{privateRecipeDao?.insert(transformPrivateRecipeToPrivateREcipeDB(privateRecipe))}
    }

    override fun getRecipe(id: String): LiveData<PrivateRecipe> {
        TODO()   //Wieso ist denn einmal die id ein Int und eimal ein String?
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
}