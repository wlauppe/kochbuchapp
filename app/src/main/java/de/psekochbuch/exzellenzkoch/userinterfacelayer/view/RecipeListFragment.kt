package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.RecipeListFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.RecipeListAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class RecipeListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        //val viewModel = ViewModelProvider(this).get(RecipeListViewmodel::class.java)
        val viewModel : RecipeListViewmodel by viewModels {
        InjectorUtils.provideRecipeListViewmodelFactory(requireContext())
        }
        binding.recyclerViewRecipeListFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // show
        viewModel.getPrivateRecipes()
        // get adapter instance to display items in
        val adapter = RecipeListAdapter(viewModel, requireContext())
        binding.recyclerViewRecipeListFragment.adapter = adapter
        // set up observer for the list of private recipes
        // if the
        val observer = Observer<List<PrivateRecipe>> { items ->
            items?.let {
                val array = arrayListOf<PrivateRecipe>()
                array.addAll(items)
                adapter.recipes = array
            }
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewRecipeListFragment.setHasFixedSize(true)

        adapter.notifyDataSetChanged()

        // ClickListener to change fragment
        binding.buttonCreateRecipe.setOnClickListener{
            val navController:NavController = findNavController()
            navController.navigate(RecipeListFragmentDirections.actionRecipeListFragmentToCreateRecipeFragment().setRecipeID(0))
        }

        return binding.root
    }

}