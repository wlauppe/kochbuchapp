package com.example.kochbuchappmagnus.datalayer.repositoryimpl.repository

import android.app.Application
import com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao.PrivateRecipeDAO
import de.psekochbuch.exzellenzkoch.datalayer.AppDatabase
import de.psekochbuch.exzellenzkoch.datalayer.repository.PrivateRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrivateRecipeRepositoryImplementation constructor(private val database:AppDatabase,
                                                        app:Application) :PrivateRecipeRepository{

    var currentDb:AppDatabase = AppDatabase().getDatabase(app.applicationContext)

    private var recipeDao: PrivateRecipeDAO? = currentDb.getPrivateRecipeDAO()

   // private var allRecipes:LiveData<List<PrivateRecipe>>? = recipeDao.getAllRecipes()









    suspend fun refreshPrivateRecipes(){
        withContext(Dispatchers.IO){

        }
    }

}