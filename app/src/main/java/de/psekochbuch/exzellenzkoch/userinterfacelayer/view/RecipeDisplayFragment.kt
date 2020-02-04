package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.databinding.PublicRecipeSearchFragmentBinding
import de.psekochbuch.exzellenzkoch.databinding.RecipeDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.PublicRecipeSearchViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel

class RecipeDisplayFragment : Fragment(){

    private lateinit var binding: RecipeDisplayFragmentBinding
    private lateinit var viewModel: RecipeDisplayViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = RecipeDisplayFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(RecipeDisplayViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.recipeDisplayViewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //recieving the recipe name through bundle
        var recipeName = arguments?.let { RecipeDisplayFragmentArgs.fromBundle(it).recipeTitle }

        Toast.makeText(requireContext(), recipeName.toString(), Toast.LENGTH_SHORT).show()
    }
}