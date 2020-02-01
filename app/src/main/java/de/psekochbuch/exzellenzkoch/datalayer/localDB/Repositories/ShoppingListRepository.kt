package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*


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