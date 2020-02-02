package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RecipeListItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel


class RecipeListAdapter(var viewModel: RecipeListViewmodel) :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {

    //Attributes delclareted
    var navController: NavController? = null
    var viewmModel: RecipeListViewmodel = viewModel
    var names = viewModel.recipes

    //OnCreateViewHolder inflates the layout and sets binding & Navcontroller
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {

        //Initializing the attributes
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RecipeListItemBinding.inflate(inflater, parent, false)
        //TestNavcontroller
        navController = Navigation.findNavController(parent)
        return RecipeListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        //Check if the requested list of private recipes is empty. If so there is nothing to display
        if (names.value.isNullOrEmpty()) {
            //The list is null or empty -> no recipe to display -> empty list
            return 0
        }
        //The list is not null nor empty -> at least one item to display -> not-null-asserted
        return names.value!!.size
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        if (names.value.isNullOrEmpty()) {
            //The list is null or empty -> no item to bind to the recyclerview
            return
        }
        //The list is not null nor is it empty -> it exists at least one item to display
        //Set the text of the given private Recipe to the TextView for every item
        val param = names.value!![position].title
        holder.recipeListItemBinding.textViewRecipeTitleItem.text = param
        //Set the onClicklistener on the Buttons which use the navcontroller to change to another fragment
        holder.recipeListItemBinding.buttonEditRecipe.setOnClickListener {
            //TODO Use safe args to pass the required informations to the new fragment
            navController!!.navigate(R.id.action_recipeListFragment_to_createRecipeFragment)
        }
        holder.recipeListItemBinding.buttonRemoveRecipe.setOnClickListener {
            viewModel.deleteRecipe(names.value!![position].id)
        }
    }

    fun setNewItems(items: List<PrivateRecipe>) {
        if (!items.isNullOrEmpty()) {
            names.value = items.toMutableList()
            this.notifyDataSetChanged()
        }
    }


    class RecipeListViewHolder(val recipeListItemBinding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(recipeListItemBinding.root)


}