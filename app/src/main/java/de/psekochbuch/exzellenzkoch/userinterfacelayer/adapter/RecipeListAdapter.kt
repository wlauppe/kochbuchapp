package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.RecipeListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel

/**
 * Adapter class that provides logic for the AdminFragment's "Reported User" RecyclerView
 *
 *@param viewModel a required AdminViewModel for underlying functions
 * @param context provides context for Toast messages
 */
class RecipeListAdapter(val viewModel:RecipeListViewmodel, context:Context) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the user ID, the given context parameter and a list of recipes,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController:NavController ? = null
    var id : Int ? = null
    var context = context



    var recipes = arrayListOf<PrivateRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()

        }

    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val recipeListItemBinding = RecipeListItemBinding.inflate(inflater, parent, false)
        return RecipeListViewHolder(recipeListItemBinding)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {

        // get item id at position
        holder.recipeListItemBinding.value = recipes[position].title
        id = recipes[position].recipeId

        // Clicklistener of item to change fragment to edit the item
        holder.recipeListItemBinding.recipeListLayoutItem.setOnClickListener{
            //safe args to send Recipe data to CreateRecipe Fragment
            navController!!.navigate(RecipeListFragmentDirections
                .actionRecipeListFragmentToCreateRecipeFragment().setRecipeID(recipes[position].recipeId))
        }

        // Delete a recipe in the list from the ListAdapterItem
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener{

            viewModel.deleteRecipe(recipes[position].recipeId)

            recipes.removeAt(position)

            notifyDataSetChanged()

     //       notifyItemRemoved(position)
        }

        // Glide logic to provide default image, if no image is set in recipe
        var urlString = ""

        val imageView = holder.recipeListItemBinding.imageViewRecipeListItem
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
     * @param recipeListItemBinding is the binding variable for the RecyclerView item
     */
    class RecipeListViewHolder(var recipeListItemBinding: RecipeListItemBinding)
        :RecyclerView.ViewHolder(recipeListItemBinding.root)

}