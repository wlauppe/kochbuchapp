package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListAdaper(var items: List<PublicRecipe> = emptyList<PublicRecipe>(), var viewModel:DisplaySearchListViewmodel,
                              context: Context)
    : RecyclerView.Adapter<DisplaySearchListAdaper.DisplaySearchListViewHolder>() {


    //Attributes
    var navController:NavController ? = null
    var id : Int ? = null
    var context = context
    //Methodes
    fun setNewItems(newItems: List<PublicRecipe>){
        items = newItems
        this.notifyDataSetChanged()
    }

    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplaySearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         navController = parent.findNavController()
        val displaySearchListItemBinding = DisplaySearchlistListitemBinding.inflate(inflater, parent, false)
            displaySearchListItemBinding.buttonOpenRecipe.setOnClickListener{
                //sending the recipename to the recipe display fragment
                navController!!.navigate(DisplaySearchListFragmentDirections.actionDisplaySearchListFragmentToRecipeDisplayFragment().setRecipeTitle(id.toString()))
            }
        return DisplaySearchListViewHolder(displaySearchListItemBinding)
         }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: DisplaySearchListViewHolder, position: Int) {
        holder.displaySearchlistListitemBinding.value = items[position].title
         id = items[position].recipeId
        holder.displaySearchlistListitemBinding.displaySearchlistLayoutItem.setOnClickListener{
        }
        //var urlString = recipes[position].image
        var imageView = holder.displaySearchlistListitemBinding.imageViewDisplaySearchListItem
        //Dummy
        var urlString: String = "https://i.ytimg.com/vi/uZfco9h0C_s/hqdefault.jpg"
        Glide.with(context).load(urlString).into(imageView)

    }
    class DisplaySearchListViewHolder(var displaySearchlistListitemBinding: DisplaySearchlistListitemBinding)
        :RecyclerView.ViewHolder(displaySearchlistListitemBinding.root)

}