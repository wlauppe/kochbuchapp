package de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.ShoppingListDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.ShoppingList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [ShoppingList::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao?

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase::class.java, "word_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}