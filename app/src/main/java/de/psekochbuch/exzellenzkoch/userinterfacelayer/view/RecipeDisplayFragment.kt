package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
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
        var recipeID = arguments?.let { RecipeDisplayFragmentArgs.fromBundle(it).recipeID }
        viewModel.setRecipeByID(recipeID)
       // Toast.makeText(requireContext(), recipeID.toString(), Toast.LENGTH_SHORT).show()


        //binding set to the according Fragment
        binding = RecipeDisplayFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.recipeDisplayViewModel = viewModel



        //initialized navcontoller
        var navController: NavController = findNavController()

        val imageView = binding.imageViewRecipeImage
        var urlString = viewModel.recipe?.imgUrl
        if(urlString == "" || urlString.isNullOrBlank()||urlString.isNullOrEmpty()){
            urlString = "https://cdn.pixabay.com/photo/2015/05/04/10/16/vegetables-752153_1280.jpg"
        }
        context?.let { Glide.with(it).load(urlString).into(imageView) }
        return binding.root
    }
}