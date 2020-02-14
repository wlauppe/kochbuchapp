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
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.AdminFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

class AdminRecipeAdapter(var viewModel: AdminViewModel,
                         context : Context) :
    RecyclerView.Adapter<AdminRecipeAdapter.AdminRecipeViewHolder>() {
    //Attributes
    var navController:NavController ? = null
    var id : Int? = null
    var context = context

    var reportedRecipes = listOf<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //Methods

    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val adminReportedRecipeItemBinding = AdminReportedRecipeItemBinding.inflate(inflater, parent, false)
        return AdminRecipeViewHolder(adminReportedRecipeItemBinding)
    }
    override fun getItemCount(): Int {
        return reportedRecipes.size
    }
    override fun onBindViewHolder(holder: AdminRecipeViewHolder, position: Int) {
        //The admin gets the recipe title and the id
        val printString: String = reportedRecipes[position].title.toString().plus(" ID(").plus(reportedRecipes[position].recipeId.toString().plus(")"))

        holder.adminReportedRecipeItemBinding.value = printString
        id = reportedRecipes[position].recipeId


        holder.adminReportedRecipeItemBinding.buttonAdminRemoveRecipe.setOnClickListener{
                Toast.makeText(context, reportedRecipes[position].recipeId.toString(), Toast.LENGTH_SHORT).show()
                viewModel.deleteRecipe(reportedRecipes[position].recipeId)
                Toast.makeText(context, "gelöscht", Toast.LENGTH_SHORT).show()

                    //   Toast.makeText(context, recipes[position].recipeId.toString().plus(" gelöscht"), Toast.LENGTH_SHORT).show()

        }

        //SafeArgs-----------------------------------------------
        holder.adminReportedRecipeItemBinding.adminReportedRecipeItemLayout.setOnClickListener {
            //sending the recipename to the recipe display fragment
            navController!!.navigate(
                AdminFragmentDirections
                    .actionAdminFragmentToRecipeDisplayFragment()
                    .setRecipeID(reportedRecipes[position].recipeId
                    )
            )
        }

        holder.adminReportedRecipeItemBinding.buttonAdminSpare.setOnClickListener{
            viewModel.unreportRecipe(reportedRecipes[position].recipeId!!)
            Toast.makeText(context, "freigegeben", Toast.LENGTH_SHORT).show()
            //Toast.makeText(context, recipes[position].title.plus(" freigegeben"), Toast.LENGTH_SHORT).show()
        }
        //var urlString
        var urlString = ""

        val imageView = holder.adminReportedRecipeItemBinding.imageViewAdminRecipeItem
        if (reportedRecipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = reportedRecipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)


    }
    class AdminRecipeViewHolder(var adminReportedRecipeItemBinding: AdminReportedRecipeItemBinding)
        :RecyclerView.ViewHolder(adminReportedRecipeItemBinding.root)

}