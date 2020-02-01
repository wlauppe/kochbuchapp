package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter


class IngredientChapterRepository(application: Application?) {
    private val ingredientChapterDao: IngredientChapterDao? = IngredientChapterDataBase.getDatabase(application!!)?.ingredientChapterDao()
    private val ingredientAmountDao:IngredientAmountDao? = IngredientAmountDataBase.getDatabase(application!!)?.ingredientAmountDao()

    fun insert(ingredientChapter: IngredientChapter?) {
        if (ingredientChapter == null)
            return;
        IngredientAmountDataBase.databaseWriteExecutor.execute {
            var chapterId:Long? = ingredientChapterDao?.insert(IngredientChapterDB(20,0,ingredientChapter.chapter))
            for (ingredientAmount in ingredientChapter.ingredients){
                ingredientAmountDao?.insert(IngredientAmountDB(0,chapterId!!,ingredientAmount.ingredient!!,ingredientAmount.unit!!,ingredientAmount.quantity!!))
            }
        }
    }


    fun getIngredientChapterByRecipeId(chapterId:Long):List<IngredientChapterDB>{
        return ingredientChapterDao!!.getIngredientChapterByRecipeId(chapterId)
    }
}