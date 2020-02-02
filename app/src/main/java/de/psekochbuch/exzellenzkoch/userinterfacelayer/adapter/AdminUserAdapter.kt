package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedUserItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel
class AdminUserAdapter(var viewModel: AdminViewModel) : RecyclerView.Adapter<AdminUserAdapter.AdminUserViewHolder>() {

    var items : List<User> = emptyList()

    fun setNewListItems(newItems: List<User>){
        items = newItems
        this.notifyDataSetChanged()
    }

    override fun getItemCount():Int{
        return items.size
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminUserAdapter.AdminUserViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = AdminReportedUserItemBinding.inflate(inflater, parent, false)
        return AdminUserAdapter.AdminUserViewHolder(itemBinding)

    }


    override fun onBindViewHolder(holder: AdminUserViewHolder, position: Int) {
        //in the XML the variable value is used for the TextView which is displayed.
        holder.adminUserIdemBinding.value = items[position].userID

        holder.adminUserIdemBinding.buttonRemoveUser.setOnClickListener{
            viewModel.deleteUser(items[position].userID!!)
        }
    }

    class AdminUserViewHolder(val adminUserIdemBinding: AdminReportedUserItemBinding):RecyclerView.ViewHolder(adminUserIdemBinding.root)
}