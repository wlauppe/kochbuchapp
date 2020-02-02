package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

class AdminRecipeAdapter(var viewModel: AdminViewModel) : RecyclerView.Adapter<AdminRecipeAdapter.AdminRecipeViewHolder>() {

    var items : List<PublicRecipe> = emptyList()

    fun setNewListItems(newItems: List<PublicRecipe>){
        items = newItems
        this.notifyDataSetChanged()
    }

    override fun getItemCount():Int{
        return items.size
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminRecipeAdapter.AdminRecipeViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = AdminReportedRecipeItemBinding.inflate(inflater, parent, false)
        return AdminRecipeAdapter.AdminRecipeViewHolder(itemBinding)

    }


    override fun onBindViewHolder(holder: AdminRecipeViewHolder, position: Int) {
        //in the XML the variable value is used for the TextView which is displayed.
        holder.adminRecipeIdemBinding.value = items[position].title

        holder.adminRecipeIdemBinding.buttonAdminRemoveRecipe.setOnClickListener{
            viewModel.deleteRecipe(items[position].id!!)
        }
    }

    class AdminRecipeViewHolder(val adminRecipeIdemBinding: AdminReportedRecipeItemBinding):RecyclerView.ViewHolder(adminRecipeIdemBinding.root)
}
