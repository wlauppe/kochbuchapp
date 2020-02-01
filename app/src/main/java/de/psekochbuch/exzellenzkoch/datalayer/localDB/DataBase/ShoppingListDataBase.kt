package de.psekochbuch.exzellenzkoch.datalayer.localDB.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [ShoppingListDB::class], version = 1, exportSchema = false)
abstract class ShoppingListDataBase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingListDao?

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListDataBase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): ShoppingListDataBase? {
            if (INSTANCE == null) {
                synchronized(ShoppingListDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ShoppingListDataBase::class.java, "shoppinglist_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}