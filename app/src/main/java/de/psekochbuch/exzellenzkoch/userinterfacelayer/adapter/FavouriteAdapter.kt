package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.FavouriteItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.FavouriteFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FavouriteViewmodel

class FavouriteAdapter(var viewModel: FavouriteViewmodel, context: Context)
    :RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>(){

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the recipe ID, the given context parameter and a list of recipes,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController: NavController? = null
    var id : Int? = null
    var context  = context

    var favouriteRecipes = ArrayList<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val favouriteItemBinding = FavouriteItemBinding.inflate(inflater, parent, false)
        return FavouriteViewHolder(favouriteItemBinding)
    }

    override fun getItemCount(): Int {
        return favouriteRecipes.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        // get recipe id of item at position
        holder.favouriteItemBinding.value = favouriteRecipes[position].title
        id = favouriteRecipes[position].recipeId

        // Glide image logic
        var urlString = ""
        val imageView = holder.favouriteItemBinding.imageViewFavouriteItem
        if (favouriteRecipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = favouriteRecipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)

        // changing fragments on item click
        holder.favouriteItemBinding.favouriteLayoutItem.setOnClickListener {
            //sending the recipename to the recipe display fragment
            navController!!.navigate(FavouriteFragmentDirections.actionFavouriteFragmentToRecipeDisplayFragment().setRecipeID(favouriteRecipes[position].recipeId))
        }
        holder.favouriteItemBinding.buttonRemoveRecipeFromFavourites.setOnClickListener{
            viewModel.deleteRecipeFromFavourites(favouriteRecipes[position].recipeId)

            favouriteRecipes.removeAt(position)

      //      notifyItemRemoved(position)
            notifyDataSetChanged()
        }
    }


    /**
     * The ViewHolderClass provides an instance of ViewHolder, which is necessary to bind the
     * RecyclerView items to the View
     *
     * @param favouriteItemBinding is the binding variable for the RecyclerView item
     */
    class FavouriteViewHolder(var favouriteItemBinding: FavouriteItemBinding)
        : RecyclerView.ViewHolder(favouriteItemBinding.root)

}