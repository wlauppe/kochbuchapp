package de.psekochbuch.exzellenzkoch.datalayer.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = arrayOf(IngredientAmountDB::class, IngredientChapterDB::class, ShoppingListDB::class, PublicRecipeDB::class, PublicRecipeTagDB::class, PrivateRecipeDB::class, PrivateRecipeTagDB::class, TagDB::class), version = 1)
abstract class DB : RoomDatabase() {
    abstract fun ingredientAmountDao(): IngredientAmountDao?
    abstract fun shoppingListDao(): ShoppingListDao?
    abstract fun ingredientChapterDao(): IngredientChapterDao?
    abstract fun publicRecipeDao(): PublicRecipeDao?
    abstract fun publicRecipeTagDao(): PublicRecipeTagDao?
    abstract fun privateRecipeDao(): PrivateRecipeDao?
    abstract fun privateRecipeTagDao(): PrivateRecipeTagDao?
    abstract fun tagDao(): TagDao?

    companion object {
        @Volatile
        private var INSTANCE: DB? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): DB? {
            if (INSTANCE == null) {
                synchronized(DB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            DB::class.java, "database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}