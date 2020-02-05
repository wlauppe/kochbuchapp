package de.psekochbuch.exzellenzkoch.datalayer.localDB

import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.Unit
import java.util.*

class Repo(application: Application?) {
    private val publicRecipeDao: PublicRecipeDao? = DB.getDatabase(application!!)?.publicRecipeDao();
    private val ingredientChapterDao: IngredientChapterDao? = DB.getDatabase(application!!)?.ingredientChapterDao();
    private val ingredientAmountDao: IngredientAmountDao? = DB.getDatabase(application!!)?.ingredientAmountDao();
    private val publicRecipeTagDao: PublicRecipeTagDao? = DB.getDatabase(application!!)?.publicRecipeTagDao();
    private val privateRecipeDao: PrivateRecipeDao? = DB.getDatabase(application!!)?.privateRecipeDao();
    private val privateRecipeTagDao: PrivateRecipeTagDao? = DB.getDatabase(application!!)?.privateRecipeTagDao();

    fun insert(ingredientAmount: IngredientAmount?, chapterId:Long) {
        if (ingredientAmount == null)
            return;
        DB.databaseWriteExecutor.execute { ingredientAmountDao?.insert(IngredientAmountDB(0,chapterId,
            ingredientAmount.ingredient,
            ingredientAmount.unit.getText(),
            ingredientAmount.quantity
        )) }
    }

    fun getIngredientAmountByIngredientChapterId(chapterId:Long):List<IngredientAmount>{
        return ingredientAmountDao!!.getIngredientAmountByIngredientChapterId(chapterId).map{ingredient -> IngredientAmount(ingredient.name,ingredient.amount, Unit.valueOf(ingredient.unit))}
    }

    fun insert(ingredientChapter: IngredientChapter?, recipeId:Long) {
        if (ingredientChapter == null)
            return;
        DB.databaseWriteExecutor.execute {
            var chapterId:Long? = ingredientChapterDao?.insert(IngredientChapterDB(0,recipeId,ingredientChapter.chapter))
            for (ingredientAmount in ingredientChapter.ingredients){
                insert(ingredientAmount, chapterId!!)
            }
        }
    }

    fun getIngredientChapterByRecipeId(chapterId:Int):List<IngredientChapter>{
        return ingredientChapterDao!!.getIngredientChapterByRecipeId(chapterId).map{chapter -> IngredientChapter(chapter.title,getIngredientAmountByIngredientChapterId(chapter.id)) }
    }



    fun insert(publicRecipe: PublicRecipe?, id: Int) {
        if (publicRecipe == null)
            return;
        //PublicREcipe entity fehlt ein userid feld (Alle ID sollen Long sein!)
        DB.databaseWriteExecutor.execute {
            val recipeId:Long? = publicRecipeDao?.insert(PublicRecipeDB(id,publicRecipe.title, publicRecipe.ingredientsText ,publicRecipe.preparation,publicRecipe.picture,publicRecipe.cookingTime,publicRecipe.preparationTime,publicRecipe.user?.userID,publicRecipe.getDateAsLong(),publicRecipe.portions))
            //tags des rezeptes werden hinzugefügt
            for (tag in publicRecipe.taglist!!){
                publicRecipeTagDao?.insert(PublicRecipeTagDB(0,recipeId!!,tag))
            }
            //chapter werden hinzugefügt
            for (chapter in publicRecipe.ingredientChapter){
                insert(chapter,recipeId!!)
            }
        }
    }

    fun insert(publicRecipe: PublicRecipe?){
        insert(publicRecipe, 0)
    }

    fun getFavorites():List<PublicRecipe>{
        //return publicRecipeDao?.getAll()?.map{publicRecipeDB -> PublicRecipe(publicRecipeDB.id, publicRecipeDB.title, publicRecipeDB.ingredientText, getIngredientChapterByRecipeId(publicRecipeDB.id), fromTimestamp(publicRecipeDB.creationDate),publicRecipeDB.preparationDescription,publicRecipeTagDao?.getTagsFromRecipe(publicRecipeDB.id)?.map { tag -> tag.tag }!!,publicRecipeDB.preparationTime,publicRecipeDB.cookingTime,publicRecipeDB.,publicRecipeDB.portions)}!!
        return listOf()
    }

    fun insert(privateRecipe: PrivateRecipe){
        DB.databaseWriteExecutor.execute{
            //
        }
    }

    fun fromTimestamp(value: Long): Date {
        return value.let { Date(it) }
    }
}
==== BASE ====
