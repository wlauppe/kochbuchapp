package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

class AdminRecipeAdapter(var items: List<String> = emptyList<String>(), var viewModel: AdminViewModel) :
    RecyclerView.Adapter<AdminRecipeAdapter.AdminRecipeViewHolder>() {
    var navController: NavController? = null


    fun setNewItems(newItems: List<String>){
        items = newItems
        this.notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()

        val adminReportedRecipeItemBinding = AdminReportedRecipeItemBinding.inflate(inflater, parent, false)


        adminReportedRecipeItemBinding.buttonAdminRemoveRecipe.setOnClickListener{
            //delete the Recipe
            var recipe  = adminReportedRecipeItemBinding.textViewAdminRecipeTitel.text.toString()
            Toast.makeText(parent.context, recipe + "wurde entfernt", Toast.LENGTH_SHORT).show()
             }
        return AdminRecipeViewHolder(adminReportedRecipeItemBinding)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: AdminRecipeViewHolder, position: Int) {
        holder.adminReportedRecipeItemBinding.value = items[position]
    }
    class AdminRecipeViewHolder(var adminReportedRecipeItemBinding: AdminReportedRecipeItemBinding)
        :RecyclerView.ViewHolder(adminReportedRecipeItemBinding.root)

}