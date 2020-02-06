package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(DisplaySearchListViewmodel::class.java)

        binding.recyclerViewSearchlistFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.displaySearchListViewmodel = viewModel

        var listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!

        val exampleAdapter = DisplaySearchListAdaper(listOfRecipeNames,viewModel, requireContext())

        binding.recyclerViewSearchlistFragment.adapter = exampleAdapter

        val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
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