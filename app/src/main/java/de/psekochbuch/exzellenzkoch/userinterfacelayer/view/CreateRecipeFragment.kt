package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.CreateRecipeFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.CreateRecipeViewmodel

class CreateRecipeFragment: Fragment() {

    private lateinit var binding: CreateRecipeFragmentBinding
    private lateinit var viewModel : CreateRecipeViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = CreateRecipeFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(CreateRecipeViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.createRecipeViewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()


        binding.buttonCreateRecipeAndGotoRecipeList.setOnClickListener{
            //Create Recipe
            navController.navigate(R.id.action_createRecipeFragment_to_recipeListFragment)
        }

        return binding.root
    }

}