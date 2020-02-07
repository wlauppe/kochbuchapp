package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.R.attr.bitmap
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.CreateRecipeFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.CreateRecipeViewmodel


class CreateRecipeFragment : Fragment() {

    private lateinit var binding: CreateRecipeFragmentBinding

    //MEthodes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel : CreateRecipeViewmodel by viewModels {
            InjectorUtils.provideCreateRecipeViewModelFactory(requireContext())
        }

        //SafeArgs---------------------------
        var recipeID = arguments?.let { CreateRecipeFragmentArgs.fromBundle(it).recipeID }

        if(recipeID != null) {
            viewModel.setRecipeByID(recipeID)
            Toast.makeText(requireContext(), recipeID.toString(), Toast.LENGTH_SHORT).show()

        }

        //binding set to the according Fragment
        binding = CreateRecipeFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders


        //Sets according viewmodel from XML to this fragment
        binding.createRecipeViewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()

        //binding viewmodel with xml components


        binding.buttonCreateRecipeAndGotoRecipeList.setOnClickListener {
            //Create Recipe
            Toast.makeText(
                requireContext(),
                "Rezept zur Rezeptliste hinzugefügt",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.saveRecipe()

            navController.navigate(R.id.action_createRecipeFragment_to_recipeListFragment)
        }

        //Image intent
        binding.imageButtonRecipeImage.setOnClickListener{

        }

        return binding.root
    }


}