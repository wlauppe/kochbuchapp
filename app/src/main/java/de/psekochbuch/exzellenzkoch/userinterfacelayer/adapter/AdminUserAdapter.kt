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

/**
 * Adapter class that provides logic for the AdminFragment's "Reported User" RecyclerView
 *
 *@param viewModel a required AdminViewModel for underlying functions
 *@param context is a param necessary for the Toast message
 */
class AdminUserAdapter(var viewModel: AdminViewModel,
                       context:Context) :
    RecyclerView.Adapter<AdminUserAdapter.AdminUserViewHolder>() {

    /**
     * Class attributes contain the Navigation Controller for navigating between Fragments,
     * the user ID, the given context parameter and a list of Reported Users,
     * which is an observable field and notifies every observer if data in the Adapter is changed.
     */
    var navController: NavController? = null
    var id : String? = null
    var context = context
    var reportedUser = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val adminReportedUserItemBinding = AdminReportedUserItemBinding.inflate(inflater, parent, false)
        return AdminUserViewHolder(adminReportedUserItemBinding)
    }

    override fun getItemCount(): Int {
        return reportedUser.size
    }

    override fun onBindViewHolder(holder: AdminUserViewHolder, position: Int) {

        // get the reported user's id at the index position in the RecyclerView
        holder.adminReportedUserItemBinding.value = reportedUser[position].userId
        id = reportedUser[position].userId

        // button logic for deleting a user
        holder.adminReportedUserItemBinding.buttonRemoveUser.setOnClickListener{
            viewModel.deleteUser(reportedUser[position].userId)
            holder.itemView.visibility = View.GONE
            notifyItemRangeChanged(position, reportedUser.size)
            notifyDataSetChanged()
            Toast.makeText(context, "gelöscht", Toast.LENGTH_SHORT).show()
        }

        // SafeArgs for sending the user name to the user display fragment when clicked on the item
        holder.adminReportedUserItemBinding.adminReportedUserItemLayout.setOnClickListener {
            navController!!.navigate(
                AdminFragmentDirections
                    .actionAdminFragmentToProfileDisplayFragment()
                    .setUserID(reportedUser[position].userId
                    )
            )
        }

        // button logic for sparing a user from deletion
        holder.adminReportedUserItemBinding.buttonSpareUser.setOnClickListener{
            //spare user
            viewModel.unreportUser(reportedUser[position].userId)
            holder.itemView.visibility = View.GONE
            notifyItemRangeChanged(position, reportedUser.size)
            notifyDataSetChanged()
            Toast.makeText(context, "freigegeben", Toast.LENGTH_SHORT).show()
        }


        // image logic from Glide
        var urlString = ""
        val imageView = holder.adminReportedUserItemBinding.imageViewAdminUserItem
        if (reportedUser[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/chef_avatar.png"
        } else {
            urlString = reportedUser[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)

    }

    /**
     * The ViewHolderClass provides an instance of ViewHolder, which is necessary to bind the
     * RecyclerView items to the View
     *
     * @param adminReportedUserItemBinding is the binding variable for the RecyclerView item
     */
    class AdminUserViewHolder(var adminReportedUserItemBinding: AdminReportedUserItemBinding)
        :RecyclerView.ViewHolder(adminReportedUserItemBinding.root)
    
}