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
import de.psekochbuch.exzellenzkoch.databinding.AdminFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminRecipeAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminUserAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class AdminFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = AdminFragmentBinding.inflate(inflater, container, false)

        // Provide ViewModel
        val viewModel : AdminViewModel by viewModels {
            InjectorUtils.provideAdminViewModelFactory(requireContext())
        }

        // Bind RecyclerViews to their .xml layout file
        binding.recyclerViewAdminRecipes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewAdminUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // new adapter instances to handle the RecyclerView items
        val recipeAdapter = AdminRecipeAdapter(viewModel, requireContext())
        val userAdapter = AdminUserAdapter(viewModel, requireContext())
        binding.recyclerViewAdminRecipes.adapter = recipeAdapter
        binding.recyclerViewAdminUsers.adapter = userAdapter

        // set observer patterns for the Adapters
        val recipeObserver = Observer<List<PublicRecipe>> { recipes ->
            recipes?.let {
                recipeAdapter.reportedRecipes = recipes}
        }
        val userObserver = Observer<List<User>> { user ->
            user?.let {
                userAdapter.reportedUser = user}
        }

        // set which ViewModel attributes should be observed
        viewModel.recipes.observe(this.viewLifecycleOwner, recipeObserver)
        viewModel.users.observe(this.viewLifecycleOwner, userObserver)

        binding.recyclerViewAdminUsers.setHasFixedSize(true)
        binding.recyclerViewAdminRecipes.setHasFixedSize(true)

        /* TODO delete
        //Safeargs werden hier aus dem Bundle gezogen
        var title = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitleToDisplay }
        var tags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        var ingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        Toast.makeText(requireContext(), title.toString() + ingredients.toString() + tags.toString(), Toast.LENGTH_SHORT).show()
         */

        return binding.root
    }
}
