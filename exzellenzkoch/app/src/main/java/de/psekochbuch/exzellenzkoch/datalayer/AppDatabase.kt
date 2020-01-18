package de.psekochbuch.exzellenzkoch.datalayer

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao.PrivateRecipeDAO
import de.psekochbuch.exzellenzkoch.domainlayer.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.PublicRecipe

@Database(entities = arrayOf(PublicRecipe::class, IngredientChapter::class, IngredientAmount::class,
    PublicRecipe::class, PrivateRecipe::class), version = 1)
class AppDatabase(): RoomDatabase() {


    private lateinit var INSTANCE: AppDatabase

    //DAOS
    private var privateRecipeDAO:PrivateRecipeDAO? = null

    //Constructor
    fun getDatabase(context: Context):AppDatabase{
        synchronized(AppDatabase::class.java){
            if(!::INSTANCE.isInitialized){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "app").fallbackToDestructiveMigration().build()
            }
        }
        return INSTANCE
    }
    //-------------------------------------
    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun getPrivateRecipeDAO():PrivateRecipeDAO?{
        return this.privateRecipeDAO
    }
}