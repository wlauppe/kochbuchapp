package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListAdaper(context: Context)
    : RecyclerView.Adapter<DisplaySearchListAdaper.DisplaySearchListViewHolder>() {
    //Attributes
    var navController: NavController? = null
    var id: Int? = null
    var context = context

    /**
     * Notify every observer of data changes
     */
    var recipes = listOf<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplaySearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val displaySearchListItemBinding =
            DisplaySearchlistListitemBinding.inflate(inflater, parent, false)
        return DisplaySearchListViewHolder(displaySearchListItemBinding)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: DisplaySearchListViewHolder, position: Int) {
        holder.displaySearchlistListitemBinding.value = recipes[position].title
        holder.displaySearchlistListitemBinding.displaySearchlistLayoutItem.setOnClickListener {
            //sending the recipename to the recipe display fragment
            navController!!.navigate(DisplaySearchListFragmentDirections
                .actionDisplaySearchListFragmentToRecipeDisplayFragment()
                .setRecipeID(recipes[position].recipeId
                )
            )
            //Toast.makeText(context, items[position].recipeId.toString(), Toast.LENGTH_SHORT).show()
        }
        //var urlString
        var urlString = ""

        val imageView = holder.displaySearchlistListitemBinding.imageViewDisplaySearchListItem
        if (recipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = recipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)
    }

    class DisplaySearchListViewHolder(var displaySearchlistListitemBinding: DisplaySearchlistListitemBinding) :
        RecyclerView.ViewHolder(displaySearchlistListitemBinding.root)

}