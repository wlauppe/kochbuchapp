package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.kochbuchappmagnus.domainlayer.ShoppingList
import de.psekochbuch.exzellenzkoch.domainlayer.entity.IngredientChapter


@Dao
interface ShoppingListDAO {

    @Insert
    fun insert(shoppingList: ShoppingList)

    @Delete
    fun delete(shoppingList: ShoppingList)

    @Update
    fun update(shoppingList: ShoppingList)
}