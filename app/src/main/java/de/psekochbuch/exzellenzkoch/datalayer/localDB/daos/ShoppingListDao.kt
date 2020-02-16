package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.ShoppingListDB

@Dao
interface ShoppingListDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insert(shoppingList: ShoppingListDB);

    @Query("SELECT * from shoppingList")
    fun getShoppingList():List<ShoppingListDB>;

    //Diese methode ist nur fürs Testen nütlich
    @Query("SELECT * from shoppingList where id = :id")
    fun getShoppingListItemById(id:Int): ShoppingListDB;
}