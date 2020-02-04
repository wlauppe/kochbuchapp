package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayRecipeItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayAdapter(var items: List<PublicRecipe> = emptyList<PublicRecipe>(),viewModel: ProfileDisplayViewmodel) :
    RecyclerView.Adapter<ProfileDisplayAdapter.ProfileDisplayViewHolder>() {
    //Attributes
    var navController:NavController ? = null
    var id : Int ? = null
    //Methodes
    fun setNewItems(newItems: List<PublicRecipe>){
        items = newItems
        this.notifyDataSetChanged()
    }

    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDisplayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val profileDisplayRecipeItemBinding = ProfileDisplayRecipeItemBinding.inflate(inflater, parent, false)
        return ProfileDisplayViewHolder(profileDisplayRecipeItemBinding)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ProfileDisplayViewHolder, position: Int) {
        holder.profileDisplayRecipeItemBinding.value = items[position].title
        id = items[position].id
        holder.profileDisplayRecipeItemBinding.buttonGoto.setOnClickListener{
            navController!!.navigate(R.id.action_profileDisplayFragment_to_recipeDisplayFragment)
        }
    }
    class ProfileDisplayViewHolder(var profileDisplayRecipeItemBinding: ProfileDisplayRecipeItemBinding)
        :RecyclerView.ViewHolder(profileDisplayRecipeItemBinding.root)

}