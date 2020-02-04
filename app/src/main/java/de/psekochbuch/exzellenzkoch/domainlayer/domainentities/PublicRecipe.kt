package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import java.security.Timestamp

class PublicRecipe constructor(ingredients: List<IngredientChapter>, title:String,
                               rating:Double, preparation:String,
                               tags:List<String>, preparationTime:Int,
                               cookingTime: Int, imgSrcUrl : String?, portions:Int ){



    var id: Int? = null
    var ingredientChapter: List<IngredientChapter> = ingredients
    var creationTimeStamp: Timestamp? = null
    var title:String? = title
    var rating: Double? = rating
    var preparation: String? = preparation
    var tags: List<String>? = tags
    var preparationTime: Int? = preparationTime
    var cookingTime: Int? = cookingTime
    var image: Image? = null
    var imgSrcUrl: String? = imgSrcUrl
    var portions: Int? = portions

    constructor(parcel: Parcel) : this(
        TODO("ingredients"),
        TODO("title"),
        TODO("rating"),
        TODO("preparation"),
        TODO("tags"),
        TODO("preparationTime"),
        TODO("cookingTime"),
        TODO("image"),
        TODO("portions")
    ) {
        title = parcel.readString()
        rating = parcel.readValue(Double::class.java.classLoader) as? Double
        preparation = parcel.readString()
        tags = parcel.createStringArrayList()
        preparationTime = parcel.readValue(Int::class.java.classLoader) as? Int
        cookingTime = parcel.readValue(Int::class.java.classLoader) as? Int
        portions = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeValue(rating)
        parcel.writeString(preparation)
        parcel.writeStringList(tags)
        parcel.writeValue(preparationTime)
        parcel.writeValue(cookingTime)
        parcel.writeValue(portions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PublicRecipe> {
        override fun createFromParcel(parcel: Parcel): PublicRecipe {
            return PublicRecipe(parcel)
        }

        override fun newArray(size: Int): Array<PublicRecipe?> {
            return arrayOfNulls(size)
        }
    }


}