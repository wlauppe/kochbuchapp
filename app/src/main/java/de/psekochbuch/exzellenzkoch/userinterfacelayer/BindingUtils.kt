package de.psekochbuch.exzellenzkoch.userinterfacelayer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class BindingUtils {

    @BindingAdapter("")
    fun ImageView.setRecipeImage(item: PublicRecipe) {
        //setImageURI(item.imgUrl) // TODO parse
    }

}