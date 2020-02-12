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
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedUserItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.AdminFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

class AdminUserAdapter(var users: List<User> = emptyList<User>(), var viewModel: AdminViewModel,
                       context:Context) :
    RecyclerView.Adapter<AdminUserAdapter.AdminUserViewHolder>() {
    //Attributes
    var navController: NavController? = null
    var id : String? = null
    var context = context
    //Methodes
    fun setNewItems(newUsers: List<User>){
        users = newUsers
        this.notifyDataSetChanged()
    }
    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val adminReportedUserItemBinding = AdminReportedUserItemBinding.inflate(inflater, parent, false)
        return AdminUserViewHolder(adminReportedUserItemBinding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: AdminUserViewHolder, position: Int) {
        holder.adminReportedUserItemBinding.value = users[position].userId
        id = users[position].userId

        holder.adminReportedUserItemBinding.buttonRemoveUser.setOnClickListener{
            viewModel.deleteUser(users[position].userId)
            setNewItems(users)
           Toast.makeText(context, "gelöscht", Toast.LENGTH_SHORT).show()
        }

        //safeArgs -------------------------------------------
        holder.adminReportedUserItemBinding.adminReportedUserItemLayout.setOnClickListener {
                //sending the recipename to the user display fragment

            navController!!.navigate(
                AdminFragmentDirections
                    .actionAdminFragmentToProfileDisplayFragment()
                    .setUserID(users[position].userId
                    )
            )
        }

        holder.adminReportedUserItemBinding.buttonSpareUser.setOnClickListener{
            //spare user
            viewModel.unreportUser(users[position].userId)
            setNewItems(users)
            Toast.makeText(context, "freigegeben", Toast.LENGTH_SHORT).show()
        }


        //var urlString
        var urlString = ""

        val imageView = holder.adminReportedUserItemBinding.imageViewAdminUserItem
        if (users[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/chef_avatar.png"
        } else {
            urlString = users[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)

    }
    class AdminUserViewHolder(var adminReportedUserItemBinding: AdminReportedUserItemBinding)
        :RecyclerView.ViewHolder(adminReportedUserItemBinding.root)
    
}