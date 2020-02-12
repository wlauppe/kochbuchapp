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
import java.lang.Thread.sleep

class DisplaySearchListFragment : Fragment(){
    private lateinit var bindingTwo : DisplaySearchlistFragmentBinding
    private lateinit var viewmodelTwo : DisplaySearchListViewmodel
    var bundle: Bundle? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //binding and ViewModel init
        val viewModel : DisplaySearchListViewmodel by viewModels {
            InjectorUtils.provideDisplaySearchListViewModelFactory(requireContext())
        }
        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)
        binding.recyclerViewSearchlistFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.displaySearchListViewmodel = viewModel


        // safeargs are passed down from the input
        bundle = savedInstanceState
        val recipeSearchTitle = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitle }
        val recipeSearchingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        val recipeSearchTags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        viewModel.getPublicRecipes(recipeSearchTitle, recipeSearchingredients, recipeSearchTags)


        val listOfRecipesToSearch: List<PublicRecipe>
        sleep(500)
        val listOfRecipeNames: List<PublicRecipe> = viewModel.recipes.value!!
        val exampleAdapter = DisplaySearchListAdaper(listOfRecipeNames,viewModel, requireContext())
            binding.recyclerViewSearchlistFragment.adapter = exampleAdapter


        val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewSearchlistFragment.setHasFixedSize(true)


        // Radio button logic
        binding.radioButtonVegan.setOnClickListener{
           var sortedRecipes =  viewmodelTwo.sortByVegan()
            val adapter = DisplaySearchListAdaper(sortedRecipes,viewmodelTwo, requireContext())
            binding.recyclerViewSearchlistFragment.adapter = adapter

            val observer = Observer<List<PublicRecipe>> { items ->
                exampleAdapter.setNewItems(items)
            }
            viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        }
        binding.radioButtonVegetarian.setOnClickListener{
            var sortedRecipesVegetarian =  viewmodelTwo.sortByVegetarian()
            val adapterVegetarian = DisplaySearchListAdaper(sortedRecipesVegetarian,viewmodelTwo, requireContext())
            binding.recyclerViewSearchlistFragment.adapter = adapterVegetarian
            val observer = Observer<List<PublicRecipe>> { items ->
                exampleAdapter.setNewItems(items)
            }
            viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        }
        binding.radioButtonDate.setOnClickListener{
            viewModel.sortByDate()

            var sortedRecipesDate =  viewmodelTwo.recipes.value!!
            val adapterDate = DisplaySearchListAdaper(sortedRecipesDate,viewmodelTwo, requireContext())
            binding.recyclerViewSearchlistFragment.adapter = adapterDate
            val observer = Observer<List<PublicRecipe>> { items ->
                exampleAdapter.setNewItems(items)
            }
            viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        }
        return binding.root
    }

    }




