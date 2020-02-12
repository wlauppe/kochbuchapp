package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.RecipeListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel


class RecipeListAdapter(var items: List<PrivateRecipe> = emptyList<PrivateRecipe>(), var viewModel: RecipeListViewmodel,
                        context:Context) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    //Attributes
    var navController:NavController ? = null
    var id : Int ? = null
    var context = context
    //Methodes
    fun setNewItems(newItems: List<PrivateRecipe>){
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

        id = items[position].recipeId


        //Clicklistener--------------------
        holder.recipeListItemBinding.recipeListLayoutItem.setOnClickListener{
            //safe args to send Recipe data to editRecipe Fragment
            navController!!.navigate(RecipeListFragmentDirections
                .actionRecipeListFragmentToCreateRecipeFragment().setRecipeID(items[position].recipeId))
        }
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener{
            viewModel.deleteRecipe(items[position].recipeId)
            items[position].title.plus("(gelöscht)")
        }


        //var urlString
        var urlString = ""

        val imageView = holder.recipeListItemBinding.imageViewRecipeListItem
        if (items[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = items[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)



    }
    class RecipeListViewHolder(var recipeListItemBinding: RecipeListItemBinding)
        :RecyclerView.ViewHolder(recipeListItemBinding.root)

}