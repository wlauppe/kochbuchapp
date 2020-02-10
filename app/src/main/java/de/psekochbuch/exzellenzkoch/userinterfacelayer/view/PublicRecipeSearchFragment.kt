package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
        //binding set to the according Fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.public_recipe_search_fragment, container, false)
        //viewmodel
        val viewModel : DisplaySearchListViewmodel by viewModels {
            InjectorUtils.provideDisplaySearchListViewModelFactory(requireContext())
        }
        //Sets according viewmodel from XML to this fragment
        binding.publicRecipeSearchViewModel = viewModel
        //initialized navcontoller
        val navController: NavController = findNavController()
        binding.buttonSearchRecipeSearch.setOnClickListener {


            //Test Safeargs
            val recipeName:String = binding.editTextSearchRecipeTitle.text.toString()
            val recipeIngredients: String = binding.editTextSearchIngredients.text.toString()
            val tags : String = binding.editTextSearchTags.text.toString()


            //safeargs sent with bundle
            navController.navigate(PublicRecipeSearchFragmentDirections.actionPublicRecipeSearchFragmentToDisplaySearchListFragment()
                .setIngredients(recipeIngredients).setRecipeTitle(recipeName).setTags(tags))



          //  navController.navigate(R.id.action_publicRecipeSearchFragment_to_displaySearchListFragment)
        }
        return binding.root
    }


}
