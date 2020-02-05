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
import de.psekochbuch.exzellenzkoch.databinding.FeedItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.FeedFragment
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.recipe_list_fragment.view.*

class FeedAdapter(var recipes: List<PublicRecipe> = emptyList<PublicRecipe>(), var viewModel: FeedViewModel
, context: Context) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    //Attributes
    var navController: NavController? = null
    var id : Int? = null
    var context  = context



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

        //var urlString = recipes[position].image
        var imageView = holder.feedItemBinding.imageViewFeedItem
        //Dummy
        var urlString: String = "https://images.unsplash.com/photo-1494253109108-2e30c049369b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
        Glide.with(context).load(urlString).into(imageView)


        holder.feedItemBinding.buttonGotoFeedRecipe.setOnClickListener{
            navController?.navigate(R.id.action_feed_to_recipeDisplayFragment)
        }

    }
    class FeedViewHolder(var feedItemBinding: FeedItemBinding)
        : RecyclerView.ViewHolder(feedItemBinding.root)

}