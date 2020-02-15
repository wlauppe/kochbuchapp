package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayRecipeItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.ProfileDisplayFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

/**
 * Adapter class that provides logic for the AdminFragment's "Reported User" RecyclerView
 *
 *@param viewModel a required AdminViewModel for underlying functions
 * @param context provides context for Toast messages
 */
class ProfileDisplayAdapter(viewModel: ProfileDisplayViewmodel, context: Context) :
    RecyclerView.Adapter<ProfileDisplayAdapter.ProfileDisplayViewHolder>() {

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the user ID, the given context parameter and a list of recipes,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController:NavController ? = null
    var id : Int ? = null
    var context = context

    var recipes = listOf<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDisplayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val profileDisplayRecipeItemBinding = ProfileDisplayRecipeItemBinding.inflate(inflater, parent, false)
        return ProfileDisplayViewHolder(profileDisplayRecipeItemBinding)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ProfileDisplayViewHolder, position: Int) {
        // get id of item at position
        holder.profileDisplayRecipeItemBinding.value = recipes[position].title
        id = recipes[position].recipeId

        // logic to set a default image if no image is provided in the recipe
        var urlString = recipes[position].imgUrl
        if(urlString == ""){
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        }
        val imageView = holder.profileDisplayRecipeItemBinding.imageViewProfileDisplayRecipe
        Glide.with(context).load(urlString).into(imageView)

        // change fragment on item click
        holder.profileDisplayRecipeItemBinding.profileDisplayRecipeLayoutItem.setOnClickListener{

            navController!!.navigate(ProfileDisplayFragmentDirections.actionProfileDisplayFragmentToRecipeDisplayFragment()
                .setRecipeID(recipes[position].recipeId))
        }

    }

    /**
     * The ViewHolderClass provides an instance of ViewHolder, which is necessary to bind the
     * RecyclerView items to the View
     *
     * @param profileDisplayRecipeItemBinding is the binding variable for the RecyclerView item
     */
    class ProfileDisplayViewHolder(var profileDisplayRecipeItemBinding: ProfileDisplayRecipeItemBinding)
        :RecyclerView.ViewHolder(profileDisplayRecipeItemBinding.root)

}