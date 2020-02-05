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
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminRecipeAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminUserAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.DisplaySearchListAdaper
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel
import kotlinx.android.synthetic.main.recipe_list_item.*

class AdminFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = AdminFragmentBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
        binding.recyclerViewAdminRecipes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.recyclerViewAdminUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        var listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!
        var listOfUser : List<User> = viewModel.users.value!!

        val exampleAdapter = AdminRecipeAdapter(listOfRecipeNames,viewModel, requireContext())
        val userAdapter = AdminUserAdapter(listOfUser, viewModel, requireContext())

        binding.recyclerViewAdminRecipes.adapter = exampleAdapter
        binding.recyclerViewAdminUsers.adapter = userAdapter


        val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
        }
        val observerUser = Observer<List<User>> {
                users -> userAdapter.setNewItems(users)
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        viewModel.users.observe(this.viewLifecycleOwner, observerUser)

        binding.recyclerViewAdminUsers.setHasFixedSize(true)
        binding.recyclerViewAdminRecipes.setHasFixedSize(true)

        /*
        //Safeargs werden hier aus dem Bundle gezogen
        var title = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitleToDisplay }
        var tags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        var ingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        Toast.makeText(requireContext(), title.toString() + ingredients.toString() + tags.toString(), Toast.LENGTH_SHORT).show()
         */
        return binding.root
    }
}
