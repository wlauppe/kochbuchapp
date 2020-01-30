package com.example.simplekochbuchapp.domainlayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shoppingList")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var nameIngredient:String,
    var unit:String,
    var amount:Int
    )
