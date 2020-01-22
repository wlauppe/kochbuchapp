package de.psekochbuch.exzellenzkoch.domainlayer

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
class PrivateRecipe : Recipe() {

    @PrimaryKey(autoGenerate = true)
    override var id:Int? = null

    override var ingredients:String? = null

    override var title:String? = null

    override var preparationDescription: String? = null

    override var preparationTime:Int? = null

    override var cookingTime:Int? = null

    override var image: Image? = null

    override var portions:Int? = null



    //var comments:MutableList<Comment>? = null








}