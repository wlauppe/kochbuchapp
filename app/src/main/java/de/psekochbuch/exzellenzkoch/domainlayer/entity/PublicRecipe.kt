package de.psekochbuch.exzellenzkoch.domainlayer.entity



import android.media.Image
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
class PublicRecipe constructor(
    ingredients:String,
    title:String,
    preparationDescription:String,
    preparationTime:Int,
    cookingTime:Int,
    image:Image,
    portions:Int,
    tags:MutableList<String>

) : Recipe(ingredients, image, title, preparationDescription, preparationTime, cookingTime,
    tags, portions)
{


    //Attributes
    @PrimaryKey(autoGenerate = true)
    override var id:Int? = null

    override var ingredients:String? = ingredients

    override var title:String? = title

    override var preparationDescription: String? = preparationDescription

    override var preparationTime:Int? = preparationTime

    override var cookingTime:Int? = cookingTime

    override var image:Image? = image

    override var portions:Int? = portions

    override var tags:MutableList<String>? = tags


    var creationTimeStamp:Timestamp = this.updateTimeStamp()

    var rating:Int? = null
    @Embedded
    var chapters:MutableList<IngredientChapter> ? = null




    //var comments:MutableList<Comment>? = null











    //Functions

    private fun updateTimeStamp(): Timestamp {
    return Timestamp(System.currentTimeMillis())
    }

    fun report(){
        //TODO
    }

}

