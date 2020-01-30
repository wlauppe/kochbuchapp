package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.ShoppingListDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase.WordRoomDatabase
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.ShoppingList


class ShoppingListRepository(application: Application?) {
    private val shoppingListDao: ShoppingListDao? = WordRoomDatabase.getDatabase(application!!)?.shoppingListDao();

    fun insert(shoppingList: ShoppingList?) {
        if (shoppingList == null)
            return;
        WordRoomDatabase.databaseWriteExecutor.execute { shoppingListDao?.insert(shoppingList) }
    }


    fun get():List<ShoppingList>{
        return shoppingListDao!!.getShoppingList()
    }

    fun getById(id:Int): ShoppingList {
        return shoppingListDao!!.getShoppingListItemById(id)
    }
}