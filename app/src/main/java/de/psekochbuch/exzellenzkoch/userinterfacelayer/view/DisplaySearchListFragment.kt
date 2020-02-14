package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.DisplaySearchListAdaper
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel
import java.lang.Thread.sleep

class DisplaySearchListFragment : Fragment(){
    private lateinit var bindingTwo : DisplaySearchlistFragmentBinding

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

        viewModel.getPublicRecipes(recipeSearchTitle, recipeSearchingredients, recipeSearchTags)


        val adapter = DisplaySearchListAdaper(requireContext())
        binding.recyclerViewSearchlistFragment.adapter = adapter

        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                adapter.recipes = items}
        }

        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewSearchlistFragment.setHasFixedSize(true)


        /*
            // Radio button logic
            binding.radioButtonVegan.setOnClickListener{
                var sortedRecipes =  viewModel.sortByVegan()
                val adapter = DisplaySearchListAdaper(sortedRecipes,viewModel, requireContext())
                binding.recyclerViewSearchlistFragment.adapter = adapter
                val observer = Observer<List<PublicRecipe>> { items ->
                    exampleAdapter.setNewItems(items)
                }
                viewModel.recipes.observe(this.viewLifecycleOwner, observer)

            }
            binding.radioButtonVegetarian.setOnClickListener{
                var sortedRecipesVegetarian =  viewModel.sortByVegetarian()
                val adapterVegetarian = DisplaySearchListAdaper(sortedRecipesVegetarian,viewModel, requireContext())
                binding.recyclerViewSearchlistFragment.adapter = adapterVegetarian
                val observer = Observer<List<PublicRecipe>> { items ->
                    exampleAdapter.setNewItems(items)
                }
                viewModel.recipes.observe(this.viewLifecycleOwner, observer)

            }
            binding.radioButtonDate.setOnClickListener{
                viewModel.sortByDate()

                var sortedRecipesDate =  viewModel.recipes.value!!
                val adapterDate = DisplaySearchListAdaper(sortedRecipesDate,viewModel, requireContext())
                binding.recyclerViewSearchlistFragment.adapter = adapterDate
                val observer = Observer<List<PublicRecipe>> { items ->
                    exampleAdapter.setNewItems(items)
                }
                viewModel.recipes.observe(this.viewLifecycleOwner, observer)

            }
            */


            //ERROR


        return binding.root
    }

    }




