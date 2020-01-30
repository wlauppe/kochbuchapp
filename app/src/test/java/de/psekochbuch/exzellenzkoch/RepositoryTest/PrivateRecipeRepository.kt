package de.psekochbuch.exzellenzkoch.RepositoryTest

import de.psekochbuch.exzellenzkoch.datalayer.dto.PrivateRecipeTagDto
import de.psekochbuch.exzellenzkoch.datalayer.dto.SharedPrivateRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.repositoryimplementations.PrivateRecipeRepositoryImplementation
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PrivateRecipeTag
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class PrivateRecipeRepository  {
    //var repo: PrivateRecipeRepositoryImplementation = PrivateRecipeRepositoryImplementation()
    var repo:PrivateRecipeRepositoryImplementation? = null

    @Test
    fun Shared_Private_Recipe_Dto_To_Private_Recipe_Test(){
        var privateRecipeTagsDto:PrivateRecipeTagDto = PrivateRecipeTagDto("TagString")
        var privateRecipeTagsDtoList:MutableList<PrivateRecipeTagDto> = ArrayList()
        privateRecipeTagsDtoList.add(privateRecipeTagsDto)
        var creationDate:Date = Date(1L)
        var dto:SharedPrivateRecipeDto = SharedPrivateRecipeDto(1,"titel","ingredientText","description",null,1, 2,"userID", creationDate ,3, privateRecipeTagsDtoList  )

        var formattedDto :PrivateRecipe = repo!!.shared_Private_Recipe_Dto_To_Private_Recipe(dto)


        //Controll
        var tags:MutableList<String> = ArrayList()
        tags.add("TagString")
        var controll:PrivateRecipe = PrivateRecipe("ingredientText", null, "titel", "description",2, 1,tags, 3)
        assertEquals(controll.cookingTime, formattedDto.cookingTime)
        assertEquals(controll.ingredients, formattedDto.ingredients)
        //...

    }

    @Test
    fun Private_Recipe_Tag_Dto_To_Private_Recipe_Test(){
        var dto:PrivateRecipeTagDto = PrivateRecipeTagDto("tagID")
        var controll:PrivateRecipeTag = PrivateRecipeTag("tagID")
        var formattedDto = repo!!.private_Recipe_tag_Dto_To_Private_Recipe_Tag(dto)
        assertEquals(formattedDto.tagId, controll.tagId)
    }
}