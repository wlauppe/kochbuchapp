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

class PublicRecipeSearchFragment : Fragment() {

    private lateinit var binding: PublicRecipeSearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //binding xml to this Fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.public_recipe_search_fragment, container, false)
        //viewmodel vie Injector class
        val viewModel : DisplaySearchListViewmodel by viewModels {
            InjectorUtils.provideDisplaySearchListViewModelFactory(requireContext())
        }
        binding.displaySearchListViewmodel = viewModel


        //initialized navcontoller
        val navController: NavController = findNavController()

        /**
         * setOnClickListener method
         */
        binding.buttonSearchRecipeSearch.setOnClickListener {
        binding.progressBarPublicRecipeSearch.visibility.or(1)

        //Handle input values via Safeargs or pass them to viewModel
        val title: String = binding.editTextSearchRecipeTitle.text.toString()
            //viewModel.title = title
        val ingredients: String = binding.editTextSearchIngredients.text.toString()
            //viewModel.ingredients = ingredients
        val tags : String = binding.editTextSearchTags.text.toString()
            //viewModel.tags = tags


        viewModel.getPublicRecipes(viewModel.title, viewModel.ingredients, viewModel.tags)

        //safeargs sent with bundle
        navController.navigate(PublicRecipeSearchFragmentDirections.actionPublicRecipeSearchFragmentToDisplaySearchListFragment()
                .setIngredients(ingredients).setRecipeTitle(title).setTags(tags))

        }

        return binding.root
    }

}
