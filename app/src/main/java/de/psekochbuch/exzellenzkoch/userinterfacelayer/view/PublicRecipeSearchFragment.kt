package de.psekochbuch.exzellenzkoch.userinterfacelayer.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.PublicRecipeSearchFragmentBinding
import de.psekochbuch.exzellenzkoch.databinding.RecipeListFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.RecipeListAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.PublicRecipeSearchViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel

class PublicRecipeSearchFragment: Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding:PublicRecipeSearchFragmentBinding

    private lateinit var viewModel : PublicRecipeSearchViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


         binding = DataBindingUtil.inflate(inflater, R.layout.public_recipe_search_fragment, container, false)

        viewModel = ViewModelProvider(this).get(PublicRecipeSearchViewmodel::class.java)

        //Sets according viewmodel from XML to this fragment
        binding.publicRecipeSearchViewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding.searchButton.setOnClickListener(){
               // navController.navigate(R.id.action_public_recipe_search_fragment_to_display_searchlist_fragment)
        }
        //Another


    }


    override fun onDestroy() {
        super.onDestroy()
    }



}
