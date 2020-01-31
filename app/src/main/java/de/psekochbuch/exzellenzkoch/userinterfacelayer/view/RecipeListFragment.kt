package de.psekochbuch.exzellenzkoch.userinterfacelayer.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RecipeListFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.RecipeListAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel
import kotlinx.android.synthetic.main.recipe_list_fragment.view.*

class RecipeListFragment: Fragment(), View.OnClickListener {

    var navController: NavController? =null
    private lateinit var binding :RecipeListFragmentBinding
    private lateinit var viewModel : RecipeListViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_list_fragment, container, false)


        viewModel = ViewModelProvider(this).get(RecipeListViewmodel::class.java)

        //Sets according viewmodel from XML to this fragment
        binding.recipeListViewModel = viewModel


        return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        binding.buttonCreateRecipe.setOnClickListener(this)
        //Another


    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button_create_recipe -> navController!!.navigate(R.id.action_recipe_list_fragment_to_create_recipe_fragment)
        }


    }
}