package de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [IngredientAmountDB::class], version = 1, exportSchema = false)
abstract class IngredientAmountDataBase : RoomDatabase() {
    abstract fun ingredientAmountDao(): IngredientAmountDao?

    companion object {
        @Volatile
        private var INSTANCE: IngredientAmountDataBase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): IngredientAmountDataBase? {
            if (INSTANCE == null) {
                synchronized(IngredientAmountDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            IngredientAmountDataBase::class.java, "ingredientamount_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}