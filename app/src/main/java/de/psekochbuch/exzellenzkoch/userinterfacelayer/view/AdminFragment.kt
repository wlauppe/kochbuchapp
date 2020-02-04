package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.databinding.AdminFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminRecipeAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminUserAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

class AdminFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AdminFragmentBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)

        binding.recyclerViewAdminRecipes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        //RecipeAdapter and Observer

        var listOfRecipeNames = viewModel.items
        val recipeAdapter = AdminRecipeAdapter(listOfRecipeNames.value!!,viewModel)
        val observer = Observer<List<String>> { listItem ->
            recipeAdapter.setNewItems(listItem)
        }
        val userAdapter = AdminUserAdapter(viewModel)

        viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        binding.recyclerViewAdminUsers.setHasFixedSize(true)
        binding.recyclerViewAdminRecipes.setHasFixedSize(true)

        return binding.root
    }

}
