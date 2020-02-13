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

        binding.recyclerViewAdminUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!
        val listOfUser : List<User> = viewModel.users.value!!

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
