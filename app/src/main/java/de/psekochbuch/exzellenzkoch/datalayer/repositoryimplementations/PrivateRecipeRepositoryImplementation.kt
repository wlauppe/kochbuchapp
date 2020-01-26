package de.psekochbuch.exzellenzkoch.datalayer.repositoryimplementations
import android.app.Application
import de.psekochbuch.exzellenzkoch.datalayer.AppDatabase
import de.psekochbuch.exzellenzkoch.datalayer.dao.PrivateRecipeDAO
import de.psekochbuch.exzellenzkoch.datalayer.dto.PrivateRecipeTagDto
import de.psekochbuch.exzellenzkoch.datalayer.dto.SharedPrivateRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PrivateRecipeTag
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

    override fun shared_Private_Recipe_Dto_To_Private_Recipe(dto: SharedPrivateRecipeDto): PrivateRecipe {
        var formattedTags : MutableList<String> = formatTagDtos(dto.tags)
        var resultRecipe : PrivateRecipe = PrivateRecipe(dto.ingredientText, dto.picture, dto.title, dto.preparationDescription, dto.preparationTime, dto.cookingTime, formattedTags,dto.portions)
        return resultRecipe
    }

    override fun private_Recipe_tag_Dto_To_Private_Recipe_Tag(dto: PrivateRecipeTagDto): PrivateRecipeTag {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
fun formatTagDtos(list:MutableList<PrivateRecipeTagDto>):MutableList<String>{
    var resultList:MutableList<String>? = ArrayList()

    for(Tagdto in list){
                    resultList!!.add(Tagdto.tagId)

    }
    return resultList!!
}