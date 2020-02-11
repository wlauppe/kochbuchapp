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

class ProfileDisplayAdapter(var items: List<PublicRecipe> = emptyList<PublicRecipe>(),viewModel: ProfileDisplayViewmodel, context: Context) :
    RecyclerView.Adapter<ProfileDisplayAdapter.ProfileDisplayViewHolder>() {

    //Attributes
    var navController:NavController ? = null
    var id : Int ? = null
    var context = context

    //Methods
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
        id = items[position].recipeId

        val urlString = items[position].imgUrl
        if(urlString == ""){
            urlString == "file:///android_asset/exampleimages/quiche.png"
        }
        val imageView = holder.profileDisplayRecipeItemBinding.imageViewProfileDisplayRecipe
        Glide.with(context).load(urlString).into(imageView)


        holder.profileDisplayRecipeItemBinding.profileDisplayRecipeLayoutItem.setOnClickListener{

            navController!!.navigate(ProfileDisplayFragmentDirections.actionProfileDisplayFragmentToRecipeDisplayFragment().setRecipeID(items[position].recipeId))
        }

    }
    class ProfileDisplayViewHolder(var profileDisplayRecipeItemBinding: ProfileDisplayRecipeItemBinding)
        :RecyclerView.ViewHolder(profileDisplayRecipeItemBinding.root)

}