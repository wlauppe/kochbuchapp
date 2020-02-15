package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
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

/**
 * Adapter class that provides logic for the AdminFragment's "Reported Recipe" RecyclerView
 *
 *@param viewModel a required AdminViewModel for underlying functions
 *@param context is a param necessary for the Toast message
 */
class AdminRecipeAdapter(var viewModel: AdminViewModel,
                         context : Context) :
    RecyclerView.Adapter<AdminRecipeAdapter.AdminRecipeViewHolder>() {

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the recipe ID, the given context parameter and a list of Reported Recipes,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController:NavController ? = null
    var id : Int? = null
    var context = context
    var reportedRecipes = listOf<PublicRecipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


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
        val printString: String = reportedRecipes[position].title
        holder.adminReportedRecipeItemBinding.value = printString
        id = reportedRecipes[position].recipeId

        // button click action to delete item from list
        holder.adminReportedRecipeItemBinding.buttonAdminRemoveRecipe.setOnClickListener{
            viewModel.deleteRecipe(reportedRecipes[position].recipeId)
            holder.itemView.visibility = View.GONE
            notifyItemRangeChanged(position, reportedRecipes.size)
            notifyDataSetChanged()
            Toast.makeText(context, "gelöscht", Toast.LENGTH_SHORT).show()
        }

        //SafeArgs provide the recipe title for the next Fragment when clicked on the item
        holder.adminReportedRecipeItemBinding.adminReportedRecipeItemLayout.setOnClickListener {
            //sending the recipe title to the recipe display fragment
            navController!!.navigate(
                AdminFragmentDirections
                    .actionAdminFragmentToRecipeDisplayFragment()
                    .setRecipeID(reportedRecipes[position].recipeId
                    )
            )
        }

        // button click action if Admin wants to spare a recipe
        holder.adminReportedRecipeItemBinding.buttonAdminSpare.setOnClickListener{
            viewModel.unreportRecipe(reportedRecipes[position].recipeId)
            holder.itemView.visibility = View.GONE
            notifyItemRangeChanged(position, reportedRecipes.size)
            notifyDataSetChanged()
            Toast.makeText(context, "freigegeben", Toast.LENGTH_SHORT).show()
        }

        // Glide action for images
        var urlString = ""
        val imageView = holder.adminReportedRecipeItemBinding.imageViewAdminRecipeItem
        if (reportedRecipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = reportedRecipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)


    }

    /**
     * The ViewHolderClass provides an instance of ViewHolder, which is necessary to bind the
     * RecyclerView items to the View
     *
     * @param adminReportedRecipeItemBinding is the binding variable for the RecyclerView item
     */
    class AdminRecipeViewHolder(var adminReportedRecipeItemBinding: AdminReportedRecipeItemBinding)
        :RecyclerView.ViewHolder(adminReportedRecipeItemBinding.root)

}