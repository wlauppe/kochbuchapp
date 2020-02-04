package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.AdminReportedRecipeItemBinding
import de.psekochbuch.exzellenzkoch.databinding.FeedItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.recipe_list_fragment.view.*

class FeedAdapter(var recipes: List<PublicRecipe> = emptyList<PublicRecipe>(), var viewModel: FeedViewModel) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    //Attributes
    var navController: NavController? = null
    var id : Int? = null
    //Methodes
    fun setNewItems(newItems: List<PublicRecipe>){
        recipes = newItems
        this.notifyDataSetChanged()
    }
    //Overridden Methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        navController = parent.findNavController()
        val feedItemBinding = FeedItemBinding.inflate(inflater, parent, false)
        return FeedViewHolder(feedItemBinding)
    }
    override fun getItemCount(): Int {
        return recipes.size
    }
    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.feedItemBinding.value = recipes[position].id.toString()
        id = recipes[position].id
        holder.feedItemBinding.buttonGotoFeedRecipe.setOnClickListener{
            navController?.navigate(R.id.action_feed_to_recipeDisplayFragment)
        }

    }
    class FeedViewHolder(var feedItemBinding: FeedItemBinding)
        : RecyclerView.ViewHolder(feedItemBinding.root)

}