package com.example.simplekochbuchapp.domainlayer.localDB.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.simplekochbuchapp.domainlayer.localDB.Daos.ShoppingListDao
import com.example.simplekochbuchapp.domainlayer.localDB.DataBase.WordRoomDatabase
import com.example.simplekochbuchapp.domainlayer.localDB.Entities.ShoppingList


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

    fun getById(id:Int):ShoppingList{
        return shoppingListDao!!.getShoppingListItemById(id)
    }
}