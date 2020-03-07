package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.DisplaySearchListAdaper
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

/**
* The Fragment class provides logic for binding the respective .xml layout file to the class
* and calls functions from the underlying ViewModel.
* The ViewModel is provided by the ViewModelFactory, which is called here.
*/
class DisplaySearchListFragment : Fragment(){
    private lateinit var bindingTwo : DisplaySearchlistFragmentBinding

    /**
     * The bundle var is needed for the Safeargs
     */
    var bundle: Bundle? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         val TAG = "DisplaySearchList"
        //binding and ViewModel init
        val viewModel : DisplaySearchListViewmodel by viewModels {
            InjectorUtils.provideDisplaySearchListViewModelFactory(requireContext())
        }
        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)
        binding.recyclerViewSearchlistFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.displaySearchListViewmodel = viewModel


        // safeargs are passed down from the input
        val recipeSearchTitle = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitle }
        val recipeSearchingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        val recipeSearchTags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }

        // get the Public Recipe to display from the ViewModel
        viewModel.getPublicRecipes(recipeSearchTitle, recipeSearchingredients, recipeSearchTags)

        val adapter = DisplaySearchListAdaper(requireContext())
        binding.recyclerViewSearchlistFragment.adapter = adapter

        // set up observer for ViewModel and define which attributes are observed
        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                adapter.recipes = items}
        }

        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewSearchlistFragment.setHasFixedSize(true)

        binding.radioButtonDate.setOnClickListener {
            viewModel.recipes.postValue(viewModel.recipesSortedDate.value!!)
        }

        adapter.notifyDataSetChanged()

        binding.radioButtonTitel.setOnClickListener {
            viewModel.recipes.postValue(viewModel.recipesSortedTitle.value!!)
        }


        return binding.root
    }

    }




