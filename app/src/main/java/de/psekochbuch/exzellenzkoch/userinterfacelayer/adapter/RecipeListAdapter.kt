package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel


class RecipeListAdapter (var viewModel : RecipeListViewmodel): RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> (){


var navController : NavController? = null

    var viewmModel: RecipeListViewmodel? = null
    var names = viewModel.recipes





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {


        val inflater = LayoutInflater.from(parent.context)

        val itemBinding = RecipeListItemBinding.inflate(inflater, parent, false)


        //TestNavcontroller
        navController = Navigation.findNavController(parent)



        return RecipeListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        if(viewModel.names.isNullOrEmpty()){
            return 0
        }
        return viewModel.names!!.size
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {




        if(names.isNullOrEmpty()){
            return
        }
        holder.recipeListItemBinding.textViewRecipeTitleItem.setText(names!![position].title)


        holder.recipeListItemBinding.buttonEditRecipe.setOnClickListener{
             navController!!.navigate(R.id.action_recipe_list_fragment_to_create_recipe_fragment)
        }
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener{
            viewModel.deleteRecipe(names!![position].id)
        }

        }


    class RecipeListViewHolder(val recipeListItemBinding: RecipeListItemBinding): RecyclerView.ViewHolder(recipeListItemBinding.root)


}