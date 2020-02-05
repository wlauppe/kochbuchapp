package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

class AdminRecipeAdapter(var recipes: List<PublicRecipe> = emptyList<PublicRecipe>(), var viewModel: AdminViewModel,
                         context : Context) :
    RecyclerView.Adapter<AdminRecipeAdapter.AdminRecipeViewHolder>() {
    //Attributes
    var navController:NavController ? = null
    var id : Int? = null
    var context = context
    //Methodes
    fun setNewItems(newItems: List<PublicRecipe>){
        recipes = newItems
        this.notifyDataSetChanged()
    }
    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val adminReportedRecipeItemBinding = AdminReportedRecipeItemBinding.inflate(inflater, parent, false)
        return AdminRecipeViewHolder(adminReportedRecipeItemBinding)
    }
    override fun getItemCount(): Int {
        return recipes.size
    }
    override fun onBindViewHolder(holder: AdminRecipeViewHolder, position: Int) {
        //The admin gets the recipe title and the id
        val printString: String = recipes[position].title.toString().plus(" ID(").plus(recipes[position].id.toString().plus(")"))

        holder.adminReportedRecipeItemBinding.value = printString
        id = recipes[position].id
        holder.adminReportedRecipeItemBinding.buttonAdminRemoveRecipe.setOnClickListener{
            id?.let { it1 -> viewModel.deleteRecipe(it1) }
        }
        holder.adminReportedRecipeItemBinding.buttonAdminSpare.setOnClickListener{
            viewModel.spareRecipe(id)
        }

        //var urlString = recipes[position].image
        var imageView = holder.adminReportedRecipeItemBinding.imageViewAdminRecipeItem
        //Dummy
        var urlString: String = "https://pbs.twimg.com/profile_images/1186876544263872512/MA8AZsIV_400x400.jpg"
        Glide.with(context).load(urlString).into(imageView)


    }
    class AdminRecipeViewHolder(var adminReportedRecipeItemBinding: AdminReportedRecipeItemBinding)
        :RecyclerView.ViewHolder(adminReportedRecipeItemBinding.root)

}