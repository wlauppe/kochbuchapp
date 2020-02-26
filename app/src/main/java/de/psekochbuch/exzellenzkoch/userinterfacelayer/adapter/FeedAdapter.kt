package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.ButtonLoadMoreItemBinding
import de.psekochbuch.exzellenzkoch.databinding.FeedItemBinding
import de.psekochbuch.exzellenzkoch.databinding.RecyclerButtonLoadmoreBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.FeedFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel


/**
 * Adapter class that provides logic for the AdminFragment's "Reported User" RecyclerView
 *
 *@param viewModel a required AdminViewModel for underlying functions
 */
class FeedAdapter( var viewModel: FeedViewModel, context: Context) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the recipe ID, the given context parameter and a list of recipes,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController: NavController? = null
    var id : Int? = null
    var context  = context

    var feedRecipes = listOf<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):FeedViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        
        val feedItemBinding = FeedItemBinding.inflate(inflater, parent, false)
        return FeedViewHolder(feedItemBinding)
    }

    override fun getItemCount(): Int {
        return feedRecipes.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        // get recipe id of item at position
        holder.feedItemBinding.value = feedRecipes[position].title
        id = feedRecipes[position].recipeId

        // Glide image logic
        var urlString = ""
        val imageView = holder.feedItemBinding.imageViewFeedItem
        if (feedRecipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = feedRecipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)

        // changing fragments on item click
        holder.feedItemBinding.feedLayoutItem.setOnClickListener {
            //sending the recipe name to the recipe display fragment
            navController!!.navigate(
                FeedFragmentDirections
                    .actionFeedToRecipeDisplayFragment()
                    .setRecipeID(feedRecipes[position].recipeId
                    )
            )
        }

        // update the page Index to load the next page of recipes to show
        if (itemCount == (pageIndex+99)) {
            pageIndex++
        }
    }

    /**
    * The ViewHolderClass provides an instance of ViewHolder, which is necessary to bind the
    * RecyclerView items to the View
    *
    * @param feedItemBinding is the binding variable for the RecyclerView item
    */
    class FeedViewHolder(var feedItemBinding: FeedItemBinding)
        : RecyclerView.ViewHolder(feedItemBinding.root)
}