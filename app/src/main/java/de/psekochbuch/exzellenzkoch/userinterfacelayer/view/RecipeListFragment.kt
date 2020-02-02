package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RecipeListFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.RecipeListAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel

class RecipeListFragment : Fragment() {

    private lateinit var binding: RecipeListFragmentBinding
    private lateinit var viewModel: RecipeListViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(RecipeListViewmodel::class.java)
        binding.recyclerViewRecipeListFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = RecipeListAdapter(viewModel)
        binding.recyclerViewRecipeListFragment.adapter = adapter
        //Add an observer pattern to the Fragment. The Owner is the fragment and the observer are the recipes
        val observer = Observer<List<PrivateRecipe>> { items ->
            adapter.setNewItems(items)
        }
        viewModel.recipes.observe(this, observer)
        //Sets according viewmodel from XML to this fragment
        binding.recipeListViewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()
        binding.buttonCreateRecipe.setOnClickListener {
            navController.navigate(R.id.action_recipeListFragment_to_createRecipeFragment)
        }
        //Adapter einfügen und verbinden
        return binding.root
    }


}