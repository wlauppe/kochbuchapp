package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.DisplaySearchListAdaper
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val viewModel = ViewModelProvider(this).get(DisplaySearchListViewmodel::class.java)

        val recipeSearchTitle = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitle }
        val recipeSearchingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        val recipeSearchTags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        viewModel.searchRecipes(recipeSearchTitle, recipeSearchingredients, recipeSearchTags)
        //Toast.makeText(context, recipeSearchTitle.plus(recipeSearchingredients).plus(recipeSearchTags), Toast.LENGTH_LONG).show() checked

        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)
        binding.recyclerViewSearchlistFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.displaySearchListViewmodel = viewModel
        val listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!
        val exampleAdapter = DisplaySearchListAdaper(listOfRecipeNames,viewModel, requireContext())
        binding.recyclerViewSearchlistFragment.adapter = exampleAdapter

        val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
        }

        binding.radioButtonVegan.setOnClickListener{
            viewModel.sortByVegan()
        }
        binding.radioButtonVegetarian.setOnClickListener{
            viewModel.sortByVegetarian()
        }
        binding.radioButtonDate.setOnClickListener{
            viewModel.sortByDate()
        }

        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewSearchlistFragment.setHasFixedSize(true)

        /*
        //Safeargs werden hier aus dem Bundel gezogem
        var title = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitleToDisplay }
        var tags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        var ingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        Toast.makeText(requireContext(), title.toString() + ingredients.toString() + tags.toString(), Toast.LENGTH_SHORT).show()
         */
        return binding.root
    }

}