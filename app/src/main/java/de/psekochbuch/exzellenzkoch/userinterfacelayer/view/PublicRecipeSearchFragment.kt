package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.PublicRecipeSearchFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel
/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class PublicRecipeSearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding xml to this Fragment
        val binding: PublicRecipeSearchFragmentBinding = DataBindingUtil
            .inflate(inflater, R.layout.public_recipe_search_fragment, container, false)
        //viewmodel vie Injector class
        val viewModel : DisplaySearchListViewmodel by viewModels {
            InjectorUtils.provideDisplaySearchListViewModelFactory(requireContext())
        }
        binding.displaySearchListViewmodel = viewModel


        //initialized navcontoller
        val navController: NavController = findNavController()
        binding.buttonSearchRecipeSearch.setOnClickListener {
        binding.progressBarPublicRecipeSearch.visibility.or(1)


        //Handle input values via Safeargs
        val recipeName: String = binding.editTextSearchRecipeTitle.text.toString()
        val recipeIngredients: String = binding.editTextSearchIngredients.text.toString()
        val tags : String = binding.editTextSearchTags.text.toString()

        //safeargs sent with bundle to
        navController.navigate(PublicRecipeSearchFragmentDirections.actionPublicRecipeSearchFragmentToDisplaySearchListFragment()
                .setIngredients(recipeIngredients).setRecipeTitle(recipeName).setTags(tags))
        }

        return binding.root
    }

}
