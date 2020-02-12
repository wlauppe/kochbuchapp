package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.FeedItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.DisplaySearchListFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.FeedFragment
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.FeedFragmentDirections
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel
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
        holder.feedItemBinding.value = recipes[position].title
        id = recipes[position].recipeId


        //var urlString
        var urlString = ""

        val imageView = holder.feedItemBinding.imageViewFeedItem
        if (recipes[position].imgUrl == "") {
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        } else {
            urlString = recipes[position].imgUrl
        }
        Glide.with(context).load(urlString).into(imageView)



        holder.feedItemBinding.feedLayoutItem.setOnClickListener {
            //sending the recipename to the recipe display fragment
            navController!!.navigate(
                FeedFragmentDirections
                    .actionFeedToRecipeDisplayFragment()
                    .setRecipeID(recipes[position].recipeId
                    )
            )
        }


    }
    class FeedViewHolder(var feedItemBinding: FeedItemBinding)
        : RecyclerView.ViewHolder(feedItemBinding.root)

}