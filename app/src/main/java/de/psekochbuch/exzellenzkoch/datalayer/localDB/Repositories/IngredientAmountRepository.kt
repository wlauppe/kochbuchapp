package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*

class IngredientAmountRepository(application: Application?) {
    private val ingredientAmountDao: IngredientAmountDao? = IngredientAmountDataBase.getDatabase(application!!)?.ingredientAmountDao();

    fun insert(ingredientAmount: IngredientAmount?) {
        if (ingredientAmount == null)
            return;
        IngredientAmountDataBase.databaseWriteExecutor.execute { ingredientAmountDao?.insert(ingredientAmount) }
    }

    fun getIngredientAmountFromIngredientChapter(id:Int): IngredientAmount {
        return ingredientAmountDao!!.getIngredientAmountFromIngredientChapter(id)
    }
}
