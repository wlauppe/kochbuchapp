package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.PublicRecipeSearchFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.PublicRecipeSearchViewmodel

class PublicRecipeSearchFragment : Fragment() {


    private lateinit var binding: PublicRecipeSearchFragmentBinding
    private lateinit var viewModel: PublicRecipeSearchViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.public_recipe_search_fragment, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(PublicRecipeSearchViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.publicRecipeSearchViewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()
        binding.buttonSearchRecipeSearch.setOnClickListener {


            //Test Safeargs verknüpft
            var recipeName:String = binding.editTextSearchRecipeTitle.text.toString()
            var recipeIngredients: String = binding.editTextSearchIngredients.text.toString()
            var tags : String = binding.editTextSearchTags.text.toString()


//safeargs werden mit dem Bundle geschicht
            navController.navigate(PublicRecipeSearchFragmentDirections.actionPublicRecipeSearchFragmentToDisplaySearchListFragment()
                .setIngredients(recipeIngredients).setRecipeTitle(recipeName).setTags(tags))



          //  navController.navigate(R.id.action_publicRecipeSearchFragment_to_displaySearchListFragment)
        }
        return binding.root
    }


}
