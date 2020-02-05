package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class PublicRecipeRepository(application: Application?) {
    private val publicRecipeDao: PublicRecipeDao? = DB.getDatabase(application!!)?.publicRecipeDao();
    private val ingredientChapterDao: IngredientChapterDao? = DB.getDatabase(application!!)?.ingredientChapterDao();
    private val publicRecipeTagDao: PublicRecipeTagDao? = DB.getDatabase(application!!)?.publicRecipeTagDao();

    fun insert(publicRecipe: PublicRecipe?, id: Long) {
        if (publicRecipe == null)
            return;
        //PublicREcipe entity fehlt ein userid feld (Alle ID sollen Long sein!)
        DB.databaseWriteExecutor.execute {
           /* var recipeId:Long? = publicRecipeDao?.insert(PublicRecipeDB(id,publicRecipe.title!!,publicRecipe.preparation!!,publicRecipe.cookingTime!!,publicRecipe.preparationTime!!,1234,42,publicRecipe.portions!!))
            for (tag in publicRecipe.tags!!){
                publicRecipeTagDao?.insert(PublicRecipeTagDB(0,recipeId!!,tag))
            }*/
        }
    }

    fun insert(publicRecipe: PublicRecipe?){
        insert(publicRecipe, 0)
    }

    fun getAll():List<PublicRecipe>{
        //return publicRecipeDao?.getAll()?.map{publicRecipeDB -> PublicRecipe(listOf(),publicRecipeDB.title,0.0,publicRecipeDB.preparationDescription,publicRecipeTagDao?.getTagsFromRecipe(publicRecipeDB.id)?.map { tag -> tag.tag }!!,publicRecipeDB.preparationTime,publicRecipeDB.cookingTime,null,publicRecipeDB.portions)}!!
        return listOf()
    }



}