package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository
import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import java.io.File
import java.util.*

interface PublicRecipeRepository {

    /**
     * Deletes the given recipe from the server.
     * Only authors or admins can delete recipes.
     */

    @Throws
    fun getReportedPublicRecipes(): LiveData<List<PublicRecipe>>


    @Throws
    fun getPublicRecipes(): LiveData<List<PublicRecipe>>

    //Neueste Recipes werden zuerst zurueckgegeben.

    //Diese Methode gibt alle Rezepte zurück, deren Titel an irgendeiner Stelle den String title
    //haben, deren Tags eine Obermenge von tags, Zutaten eine Obermenge von ingredients,
    //deren Veröffentlichungsdatum neuer als creationDate sind.
    //Sort Order kann title, date, oder ranking sein.
    @Throws
    fun getPublicRecipes(tags:TagList, ingredients: IngredientChapter, creationDate:Date, sortOrder:String ): LiveData<List<PublicRecipe>>

    @Throws
    fun getPublicRecipe(recipeId : Int): LiveData<PublicRecipe>


    @Throws
    suspend fun  deleteRecipe(recipeId: Int)

    //veröffentlicht ein Rezept, wenn Id null ist, neu, ansonsten unter der alten Id
    //gibt die Id des neuen Rezepts zurück.
    @Throws
    suspend fun publishRecipe(publicRecipe: PublicRecipe) : Int


    @Throws
    suspend fun setRating(recipeId: Int, userId : String,value : Int)
    // Get gibts, nicht richtig?, recipe hat einfach ein Feld averageRating.

    //TODO entweder getRating inkludieren oder besser in PublicRecipe das Feld ratingaverage

    @Throws
    suspend fun setImage(recipeId : Int, ImageUrl : String)

    @Throws
    suspend fun reportRecipe(RecipeId: Int)

    @Throws
    suspend fun unreportRecipe(RecipeId : Int)

    @Throws
    fun getRecipesFromUser(userId:String): LiveData<List<PublicRecipe>>

    fun setToken(tk:String?)

}