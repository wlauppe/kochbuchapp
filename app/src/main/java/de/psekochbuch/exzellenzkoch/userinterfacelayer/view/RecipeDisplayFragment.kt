package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RecipeDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel

class RecipeDisplayFragment : Fragment(){

    private lateinit var binding: RecipeDisplayFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewmodel recieved by viewmodelproviders
        val viewModel : RecipeDisplayViewmodel by viewModels {
            InjectorUtils.provideRecipeDisplayViewModelFactory(requireContext())
        }

        //SafeArgs---------------------------
        val recipeID = arguments?.let { RecipeDisplayFragmentArgs.fromBundle(it).recipeID }
        viewModel.setRecipeByID(recipeID)
       // Toast.makeText(requireContext(), recipeID.toString(), Toast.LENGTH_SHORT).show()


        //binding set to the according Fragment
        binding = RecipeDisplayFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.recipeDisplayViewModel = viewModel

        // set options Menu shown
        setHasOptionsMenu(true)

        //initialized navcontoller
        var navController: NavController = findNavController()

        val imageView = binding.imageViewRecipeImage

        var urlString = viewModel.recipe.value?.imgUrl

        Toast.makeText(context, urlString, Toast.LENGTH_SHORT).show()

        if(urlString == "" || urlString.isNullOrBlank()||urlString.isNullOrEmpty()){
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        }
        context?.let { Glide.with(it).load(urlString).into(imageView) }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu_display_recipe, menu)
    }

}