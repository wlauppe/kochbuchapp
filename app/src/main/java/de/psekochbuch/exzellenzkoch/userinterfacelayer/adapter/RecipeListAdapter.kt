package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel


class RecipeListAdapter(var items: List<PublicRecipe> = emptyList<PublicRecipe>(), var viewModel: RecipeListViewmodel) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    //Attributes
    var navController:NavController ? = null
    var id : Int ? = null
    //Methodes
    fun setNewItems(newItems: List<PublicRecipe>){
        items = newItems
        this.notifyDataSetChanged()
    }

    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val recipeListItemBinding = RecipeListItemBinding.inflate(inflater, parent, false)
        return RecipeListViewHolder(recipeListItemBinding)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.recipeListItemBinding.value = items[position].title
        id = items[position].id
        holder.recipeListItemBinding.buttonEditRecipe.setOnClickListener{
            //safe args to send Recipe data to editRecipe Fragment
        }
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener{
            //delete recipe
            viewModel.deleteRecipe(id)
        }
    }
    class RecipeListViewHolder(var recipeListItemBinding: RecipeListItemBinding)
        :RecyclerView.ViewHolder(recipeListItemBinding.root)

}