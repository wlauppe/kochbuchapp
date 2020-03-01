package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import java.util.*


class PublicRecipeFakeRepositoryImp() : PublicRecipeRepository {
    private val TAG = "FakeRepositoryImp"
    private var recipeList: MutableList<PublicRecipe> = mutableListOf()
    var entries = 1
    //var recipeList : MutableList<PublicRecipe>

    //helper function die beim Adden die ids dass sie richtig sind anpasst.
    private fun addToList (recipe : PublicRecipe){
        var myrecipe = recipe
        val newid=recipeList.size+1
        myrecipe.recipeId=newid
        recipeList.add(myrecipe)

    }

    init {
        Log.w(TAG, "Starte init Block")
        val recipe1 = PublicRecipe(1, "trockener Sandkuchen")
        val recipe2 =
            PublicRecipe(2, "Quiche", imgUrl = "file:///android_asset/exampleimages/quiche.png")


        val ingredient = IngredientAmount("ingredientAmount", 4.4, "Esslöffel")
        val ingredientChapter = IngredientChapter(4, "test", listOf(ingredient))
        val listTags = listOf<String>("tag2", "vegan", "vegetarisch", "tag 4", "tag2", "tag 4")

        val listTagsTwo = listOf<String>("tag2", "vegetarisch", "tag 4", "tag2", "tag 4")

        val listTagsThree = listOf<String>("tag2", "vegetarisch", "vegan", "tag2", "tag 4")
        val recipe4 = PublicRecipe(
            4,
            "Leckeres Gemüse",
            "zutatenText hier kann alles drinnstehen",
            listOf(ingredientChapter),
            listTagsTwo,
            "zubereitungsbeschreibung hier kann auch alles stehen",
            imgUrl = "https://so-gesund.com/wp-content/uploads/2017/03/Obst.jpg"
        )

        val recipe3 = PublicRecipe(
            3,
            "Bratapfel",
            "", listOf(ingredientChapter) ,listTagsThree,
            imgUrl = "file:///android_asset/exampleimages/bratapfel.png"
        )

        //val list = listOf(recipe1, recipe2, recipe3, recipe4)
        // recipeList = mutableListOf<PublicRecipe>()
        Log.w(TAG, "Versuche auf Recipelistzuzugreifen")
        addToList(recipe1)
        addToList(recipe2)
        addToList(recipe3)
        addToList(recipe4)
        Log.w(TAG, "Habe habe jetzt zugegriffen")
    }

    override fun getReportedPublicRecipes(): LiveData<List<PublicRecipe>> {
        return getPublicRecipes(1)
    }


    override fun getPublicRecipes(page:Int): LiveData<List<PublicRecipe>> {
        /*val recipe1 = PublicRecipe(1,"trockener Sandkuchen")
        val recipe2 = PublicRecipe(2,"Quiche", imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val munit = Unit.EssLöffel
        val ingredient = IngredientAmount("ingredientAmount", 4.4, munit)
        val ingredientChapter = IngredientChapter(4, "test", listOf(ingredient))
        val listTags = listOf<String>("tag2", "tag 4","tag2", "tag 4","tag2", "tag 4")
        val recipe4 = PublicRecipe(4, "Tabak", "zutatenText hier kann alles drinnstehen", listOf(ingredientChapter), listTags,"zubereitungsbeschreibung hier kann auch alles stehen",imgUrl = "file:///android_asset/exampleimages/quiche.png")

        val recipe3 = PublicRecipe(3,"Bratapfel", imgUrl = "file:///android_asset/exampleimages/bratapfel.png")
        val list = listOf(recipe1,recipe2, recipe3, recipe4)


         */
        Log.w(TAG, "Auf get Public Recipes wurde jetzt zugegriffen")
        entries = recipeList.size
        Log.w(TAG, "entries = $entries")
        Log.w(TAG, "Gebe Folgende Werte zurück")

        for(recipe in recipeList) {
            Log.w(TAG, "rezept id: ${recipe.recipeId}, Titel ${recipe.title}, ImageURL ${recipe.imgUrl}")
        }

        Log.w(TAG, "Id von erstem aus der List ist $recipeList[1].recipeId()")
        val recipe1 = PublicRecipe(1, "extra tockener Sandkuchen")
        addToList(recipe1)
        val ld: LiveData<List<PublicRecipe>> = MutableLiveData(recipeList)
        return ld
    }

    override fun getPublicRecipes(
        title:String,
        tags: List<String>,
        ingredients: List<String>,
        creationDate: Date?,
        sortOrder: String
    ): LiveData<List<PublicRecipe>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPublicRecipes(
        title: String,
        tags: List<String>,
        ingredients: List<String>,
        creationDate: Date?,
        sortOrder: String,
        page: Int
    ): LiveData<List<PublicRecipe>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPublicRecipe(recipeId: Int): LiveData<PublicRecipe> {
        Log.w(TAG, "getPublicRecipe, recipeId $recipeId aufgerufen")
        for(recipe in recipeList){
            if (recipe.recipeId == recipeId){
                return MutableLiveData(recipe)
            }
        }
       Log.w(TAG, "Id nicht gefunden, gebe irgendwas zurück")
        val recipe = recipeList.get(recipeId-1)
        val ld: MutableLiveData<PublicRecipe> = MutableLiveData(recipe)
        return ld
    }

    override suspend fun deleteRecipe(recipeId: Int) {

        for(iterator in recipeList.toList()){
            if(iterator.recipeId == recipeId){
                recipeList.remove(iterator)
            }
        }

    }

    override suspend fun publishRecipe(publicRecipe: PublicRecipe): Int {
        addToList(publicRecipe)
        return publicRecipe.recipeId
    }


    override suspend fun setRating(recipeId: Int, userId: String, value: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun setImage(recipeId: Int, ImageUrl: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportRecipe(RecipeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun unreportRecipe(recipeId: Int) {

        for(iterator in recipeList.toList()){
            if(iterator.recipeId == recipeId){
                recipeList.remove(iterator)
            }
        }
         }

    override fun getRecipesFromUser(userId: String): LiveData<List<PublicRecipe>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setToken(tk: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isTokenSet(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: PublicRecipeRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PublicRecipeFakeRepositoryImp().also { instance = it }
        }
    }
}