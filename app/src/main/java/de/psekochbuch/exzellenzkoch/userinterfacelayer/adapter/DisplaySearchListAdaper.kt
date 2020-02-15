package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.nfc.Tag
import android.util.Log
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

/**
 * Adapter class that provides logic for the AdminFragment's "Reported User" RecyclerView
 *
 *@param context is a param necessary for the Toast message
 */
class DisplaySearchListAdaper(context: Context)
    : RecyclerView.Adapter<DisplaySearchListAdaper.DisplaySearchListViewHolder>() {
    var TAG = "DisplaySearchListAdapter"

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the recipe ID, the given context parameter and a list of recipes,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController: NavController? = null
    var id: Int? = null
    var context = context

    var recipes = listOf<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


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
        // button logic to go to new Fragment and see the recipe
        holder.displaySearchlistListitemBinding.displaySearchlistLayoutItem.setOnClickListener {
            //sending the recipe title to the recipe display fragment
            navController!!.navigate(DisplaySearchListFragmentDirections
                .actionDisplaySearchListFragmentToRecipeDisplayFragment()
                .setRecipeID(recipes[position].recipeId))
            Log.i(TAG,recipes[position].recipeId.toString().plus("  ist die ID"))

        }

        // Glide image logic
        var urlString = ""
        val imageView = holder.displaySearchlistListitemBinding.imageViewDisplaySearchListItem
        if (recipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = recipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)
    }

    /**
     * The ViewHolderClass provides an instance of ViewHolder, which is necessary to bind the
     * RecyclerView items to the View
     *
     * @param displaySearchlistListitemBinding is the binding variable for the RecyclerView item
     */
    class DisplaySearchListViewHolder(var displaySearchlistListitemBinding: DisplaySearchlistListitemBinding) :
        RecyclerView.ViewHolder(displaySearchlistListitemBinding.root)

}