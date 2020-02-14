package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.InjectorUtils
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
        //val viewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
        //viewmodel recieved by viewmodelproviders

        val viewModel : AdminViewModel by viewModels {
            InjectorUtils.provideAdminViewModelFactory(requireContext())
        }

        binding.recyclerViewAdminRecipes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewAdminUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val recipeAdapter = AdminRecipeAdapter(viewModel, requireContext())
        val userAdapter = AdminUserAdapter(viewModel, requireContext())

        binding.recyclerViewAdminRecipes.adapter = recipeAdapter
        binding.recyclerViewAdminUsers.adapter = userAdapter


        val recipeObserver = Observer<List<PublicRecipe>> { recipes ->
            recipes?.let {
                recipeAdapter.reportedRecipes = recipes}
        }

        val userObserver = Observer<List<User>> { user ->
            user?.let {
                userAdapter.reportedUser = user}
        }


        viewModel.recipes.observe(this.viewLifecycleOwner, recipeObserver)
        viewModel.users.observe(this.viewLifecycleOwner, userObserver)

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
