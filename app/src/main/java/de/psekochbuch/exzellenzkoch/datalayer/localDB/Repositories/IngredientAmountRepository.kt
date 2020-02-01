package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*


class IngredientAmountRepository(application: Application?) {
    private val ingredientAmountDao: IngredientAmountDao? = DB.getDatabase(application!!)?.ingredientAmountDao();

    fun insert(ingredientAmount: IngredientAmount?) {
        if (ingredientAmount == null)
            return;
        DB.databaseWriteExecutor.execute { ingredientAmountDao?.insert(IngredientAmountDB(0,0,ingredientAmount.ingredient!!,ingredientAmount.unit!!,ingredientAmount.quantity!!)) }
    }


    fun getIngredientAmountByIngredientChapterId(chapterId:Long):List<IngredientAmountDB>{
        return ingredientAmountDao!!.getIngredientAmountByIngredientChapterId(chapterId)
    }
}