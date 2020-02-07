package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.AdminFragmentDirections
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
        val printString: String = recipes[position].title.toString().plus(" ID(").plus(recipes[position].recipeId.toString().plus(")"))

        holder.adminReportedRecipeItemBinding.value = printString
        id = recipes[position].recipeId
        holder.adminReportedRecipeItemBinding.buttonAdminRemoveRecipe.setOnClickListener{
            id?.let { it1 -> viewModel.deleteRecipe(it1) }
        }
        //holder.adminReportedRecipeItemBinding.adminReportedRecipeItemLayout.setOnClickListener{
        //    navController!!.navigate(R.id.action_adminFragment_to_recipeDisplayFragment)
        //}

//SafeArgs-----------------------------------------------
        holder.adminReportedRecipeItemBinding.imageViewAdminRecipeItem.setOnClickListener {
            //sending the recipename to the recipe display fragment
            navController!!.navigate(
                AdminFragmentDirections
                    .actionAdminFragmentToRecipeDisplayFragment()
                    .setRecipeID(recipes[position].recipeId
                    )
            )
        }

        holder.adminReportedRecipeItemBinding.buttonAdminSpare.setOnClickListener{
            viewModel.unreportRecipe(id)
        }

        //var urlString
        var urlString = ""

        var imageView = holder.adminReportedRecipeItemBinding.imageViewAdminRecipeItem
        if (recipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/quiche.png"
        } else {
            urlString = recipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)


    }
    class AdminRecipeViewHolder(var adminReportedRecipeItemBinding: AdminReportedRecipeItemBinding)
        :RecyclerView.ViewHolder(adminReportedRecipeItemBinding.root)

}