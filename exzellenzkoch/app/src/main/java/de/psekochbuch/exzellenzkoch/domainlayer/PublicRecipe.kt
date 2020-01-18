package de.psekochbuch.exzellenzkoch.domainlayer



import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.psekochbuch.exzellenzkoch.domainlayer.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.Recipe
import java.sql.Timestamp

@Entity
class PublicRecipe : Recipe() {


    //Attributes
    @PrimaryKey(autoGenerate = true)
    override var id:Int? = null

    override var ingredients:String? = null
    override var title:String? = null

    override var preparationDescription: String? = null

    override var preparationTime:Int? = null

    override var cookingTime:Int? = null

    override var image:Image? = null

    override var portions:Int? = null

    override var tags:MutableList<String>? = null


    var creationTimeStamp:Timestamp = this.updateTimeStamp()
    var rating:Int? = null

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

