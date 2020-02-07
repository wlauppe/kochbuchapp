package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.*


class ShoppingListRepository(application: Application?) {
    private val shoppingListDao: ShoppingListDao? = DB.getDatabase(application!!)?.shoppingListDao();

    fun insert(shoppingList: ShoppingListDB?) {
        if (shoppingList == null)
            return;
        DB.databaseWriteExecutor.execute { shoppingListDao?.insert(shoppingList) }
    }


    fun get():List<ShoppingListDB>{
        return shoppingListDao!!.getShoppingList()
    }

    fun getById(id:Int): ShoppingListDB {
        return shoppingListDao!!.getShoppingListItemById(id)
    }
}