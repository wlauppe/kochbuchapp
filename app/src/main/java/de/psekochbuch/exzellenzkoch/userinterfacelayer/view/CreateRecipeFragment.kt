package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.CreateRecipeFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.CreateRecipeViewmodel
import java.io.File

class CreateRecipeFragment : Fragment() {

    private lateinit var binding: CreateRecipeFragmentBinding
    private lateinit var viewModel: CreateRecipeViewmodel

    //image
    var file : File? = null

    //MEthodes

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

        //binding viewmodel with xml components


        binding.buttonCreateRecipeAndGotoRecipeList.setOnClickListener {
            //Create Recipe
            Toast.makeText(
                requireContext(),
                "Rezept zur Rezeptliste hinzugefügt",
                Toast.LENGTH_SHORT
            ).show()
            navController.navigate(R.id.action_createRecipeFragment_to_recipeListFragment)
        }

        //Image
/*
        binding.imageButtonRecipeImage.setOnClickListener{
            var intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        file = File(Environment.getExternalStorageState(Environment.getDataDirectory()), "userImg.JPG")

        var tempUri = Uri.fromFile(file)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri)
            startActivityForResult(intent, 0)
        }
        */

        return binding.root
    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 0){

        }
    }

 */

}