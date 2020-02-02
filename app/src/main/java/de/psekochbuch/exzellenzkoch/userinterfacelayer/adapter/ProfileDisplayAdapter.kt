package de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayRecipeItemBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayAdapter(viewModel: ProfileDisplayViewmodel) :
    RecyclerView.Adapter<ProfileDisplayAdapter.ProfileDisplayViewHolder>() {

    //Attributes delclareted
    var navController: NavController? = null
    var viewModel: ProfileDisplayViewmodel = viewModel
    var names: LiveData<MutableList<PublicRecipe>> = viewModel.recipes

    //OnCreateViewHolder inflates the layout and sets binding & Navcontroller
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDisplayViewHolder {

        //Initializing the attributes
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ProfileDisplayRecipeItemBinding.inflate(inflater, parent, false)
        //TestNavcontroller
        navController = Navigation.findNavController(parent)

        return ProfileDisplayAdapter(viewModel)
        return ProfileDisplayViewHolder(itemBinding)
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

    override fun onBindViewHolder(holder: ProfileDisplayViewHolder, position: Int) {
        if (names.value.isNullOrEmpty()) {
            //The list is null or empty -> no item to bind to the recyclerview
            return
        }
        //The list is not null nor is it empty -> it exists at least one item to display
        //Set the text of the given private Recipe to the TextView for every item
        val param = names.value!![position].title
        holder.publiRecipeIdemBinding.textViewRecipeName.text = param
        //Set the onClicklistener on the Buttons which use the navcontroller to change to another fragment

        holder.publiRecipeIdemBinding.buttonGoto.setOnClickListener {
            //TODO Use safe args to pass the required informations to the new fragment
            navController!!.navigate(R.id.action_profileDisplayFragment_to_recipeDisplayFragment)
        }
    }


    fun setNewItems(items: List<PublicRecipe>) {
        //TODO

    }


    class ProfileDisplayViewHolder(val publiRecipeIdemBinding: ProfileDisplayRecipeItemBinding) :
        RecyclerView.ViewHolder(publiRecipeIdemBinding.root)


}