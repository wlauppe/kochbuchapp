package de.psekochbuch.exzellenzkoch.datalayer.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientAmount(@PrimaryKey(autoGenerate = true) val id: Int,
                            val chapterId: Int,
                            val nameIngredient: String,
                            val unit: String,
                            val amount: Double)