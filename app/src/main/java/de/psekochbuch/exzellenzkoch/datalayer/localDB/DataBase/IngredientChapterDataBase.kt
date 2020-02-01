package de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [IngredientChapterDB::class], version = 1, exportSchema = false)
abstract class IngredientChapterDataBase : RoomDatabase() {
    abstract fun ingredientChapterDao(): IngredientChapterDao?

    companion object {
        @Volatile
        private var INSTANCE: IngredientChapterDataBase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): IngredientChapterDataBase? {
            if (INSTANCE == null) {
                synchronized(IngredientChapterDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            IngredientChapterDataBase::class.java, "ingredientchapter_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}