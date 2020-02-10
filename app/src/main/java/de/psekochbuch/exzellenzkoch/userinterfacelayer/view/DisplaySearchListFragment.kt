package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.DisplaySearchListAdaper
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListFragment : Fragment(){
    private lateinit var bindingTwo : DisplaySearchlistFragmentBinding
    private lateinit var viewmodelTwo : DisplaySearchListViewmodel
    var bundle: Bundle? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bundle = savedInstanceState
        // viewModel
        val viewModel : DisplaySearchListViewmodel by viewModels {
            InjectorUtils.provideDisplaySearchListViewModelFactory(requireContext())
        }

        viewmodelTwo = viewModel

        var recipeSearchTitle = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitle }
        var recipeSearchingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        var recipeSearchTags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        viewModel.searchRecipes(recipeSearchTitle, recipeSearchingredients, recipeSearchTags)
        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)
        binding.recyclerViewSearchlistFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.displaySearchListViewmodel = viewModel
        bindingTwo = binding
        bindingTwo.displaySearchListViewmodel = viewmodelTwo
        var listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!
        val exampleAdapter = DisplaySearchListAdaper(listOfRecipeNames,viewModel, requireContext())
        binding.recyclerViewSearchlistFragment.adapter = exampleAdapter

        val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewSearchlistFragment.setHasFixedSize(true)
        return binding.root
    }


        //spinner
        //val options = arrayOf("Bewertung", "Datum", "Vegan", "Günstig", "vegetarisch", "süß", "Magnus")
       // val spinner = binding.spinnerSortOptions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val options = arrayOf("Bewertung", "Datum", "Vegan", "Günstig", "vegetarisch", "süß", "Magnus")
        var spinner = bindingTwo.spinnerSortOptions
        if(savedInstanceState == null){
            this.onCreate(bundle)
        }
        if (spinner != null) {
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, options)
            spinner.adapter = arrayAdapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    viewmodelTwo.sortBy(options[position])
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }



    }




}