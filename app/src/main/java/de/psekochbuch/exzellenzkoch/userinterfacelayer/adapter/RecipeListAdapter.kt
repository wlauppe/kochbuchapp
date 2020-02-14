package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.dynamic.SupportFragmentWrapper
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.RecipeListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel


class RecipeListAdapter(val viewModel:RecipeListViewmodel, context:Context) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    //Attributes
    var navController:NavController ? = null
    var id : Int ? = null
    var context = context

    /**
     * Notify every observer of changes
     */
    var recipes = listOf<PrivateRecipe>()
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
        holder.recipeListItemBinding.value = recipes[position].title

        id = recipes[position].recipeId


        //Clicklistener--------------------
        holder.recipeListItemBinding.recipeListLayoutItem.setOnClickListener{
            //safe args to send Recipe data to editRecipe Fragment
            navController!!.navigate(RecipeListFragmentDirections
                .actionRecipeListFragmentToCreateRecipeFragment().setRecipeID(recipes[position].recipeId))
        }

        /**
         * Delete a recipe in the list from the ListAdapterItem
         */
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener{

           // Toast.makeText(context, items[position].recipeId.toString(), Toast.LENGTH_SHORT).show()
            viewModel.deleteRecipe(recipes[position].recipeId)
            notifyItemRemoved(position)
            notifyDataSetChanged()

            //notifyItemRangeChanged(position,1)
           // holder.itemView.visibility = View.GONE
        }


        //var urlString
        var urlString = ""

        val imageView = holder.recipeListItemBinding.imageViewRecipeListItem
        if (recipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = recipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)

    }

    class RecipeListViewHolder(var recipeListItemBinding: RecipeListItemBinding)
        :RecyclerView.ViewHolder(recipeListItemBinding.root)

}