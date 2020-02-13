package de.psekochbuch.exzellenzkoch.userinterfacelayer

import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ViewTarget
import de.hdodenhof.circleimageview.CircleImageView
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import kotlinx.android.synthetic.main.nav_header_main.view.*

class BindingUtils private constructor(val binding: DisplaySearchlistListitemBinding) : RecyclerView.ViewHolder(binding.root){

    @BindingAdapter("publicRecipeImageFormatted")
    fun ImageView.setRecipeImage(item: PublicRecipe) {

        //GlideApp.with(this)
        //        .load("http://via.placeholder.com/300.png")
        //        .transform(new CircleCrop())
        //        .into(ivImg);

        //Glide.with(context).load(uri).apply(RequestOptions().circleCrop()).into(imageView)

        //binding.imageViewDisplaySearchListItem = Glide.with(context).load(item.imgUrl).into(imageView)
    }

    @BindingAdapter("publicRecipeTitleFormatted")
    fun TextView.setRecipeTitle(item: PublicRecipe) {
        binding.textViewRecipeName.text = item.title
    }

}