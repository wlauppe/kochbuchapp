package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistListitemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections


/* TODO delete if not used OLD ADAPTER CLASS

   class DisplaySearchListAdapter(
    var items: List<PublicRecipe> = emptyList<PublicRecipe>(),
    var viewModel: DisplaySearchListViewmodel,
    context: Context
) : ListAdapter<PublicRecipe,
        DisplaySearchListAdapter.DisplaySearchListViewHolder>(PublicRecipeItemDiffCallback()) {*/


class DisplaySearchListAdapter : ListAdapter<PublicRecipe,
        DisplaySearchListAdapter.DisplaySearchListViewHolder>(PublicRecipeItemDiffCallback()) {

    /**
     * Nested class which provides the DiffUtil logic to update the item data in the Adapter
     */
    private class PublicRecipeItemDiffCallback(): DiffUtil.ItemCallback<PublicRecipe>() {

        override fun areItemsTheSame(oldItem: PublicRecipe, newItem: PublicRecipe): Boolean {
            return oldItem.recipeId == newItem.recipeId
        }

        override fun areContentsTheSame(oldItem: PublicRecipe, newItem: PublicRecipe): Boolean {
            return oldItem.equals(newItem)
        }
    }

    /**
     * Attributes needed are navcontroler to switch the Fragment and an ID to provide as parameter
     */
    var navController: NavController? = null
    var id: Int? = null
    //var context = context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplaySearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val displaySearchListItemBinding =
            DisplaySearchlistListitemBinding.inflate(inflater, parent, false)
        return DisplaySearchListViewHolder(displaySearchListItemBinding)
    }


    override fun onBindViewHolder(holder: DisplaySearchListViewHolder, position: Int) {

        val item = getItem(position)


        holder.displaySearchlistListitemBinding.displaySearchlistLayoutItem.setOnClickListener {
            //sending the recipename to the recipe display fragment
            navController!!.navigate(DisplaySearchListFragmentDirections
                .actionDisplaySearchListFragmentToRecipeDisplayFragment()
                .setRecipeID(item.recipeId
                )
            )
        }
        //var urlString
        var urlString = ""

        val imageView = holder.displaySearchlistListitemBinding.imageViewDisplaySearchListItem
        if (item.imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = item.imgUrl
        }
        // TODO GLIDE NEEDS CONTEXT HOW TO PROVIDE??
        // Glide.with(context).load(urlString).into(imageView)
    }


    class DisplaySearchListViewHolder(var displaySearchlistListitemBinding: DisplaySearchlistListitemBinding) :
        RecyclerView.ViewHolder(displaySearchlistListitemBinding.root) {

        fun bind(item: PublicRecipe) {
            displaySearchlistListitemBinding.value = item
            displaySearchlistListitemBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DisplaySearchListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    DisplaySearchlistListitemBinding.inflate(layoutInflater, parent, false)
                return DisplaySearchListViewHolder(binding)
            }
        }
    }

}