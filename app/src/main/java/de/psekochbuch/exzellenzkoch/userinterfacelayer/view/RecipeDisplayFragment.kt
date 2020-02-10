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
        Toast.makeText(requireContext(), recipeID.toString(), Toast.LENGTH_SHORT).show()


        //binding set to the according Fragment
        binding = RecipeDisplayFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.recipeDisplayViewModel = viewModel



        //initialized navcontoller
        var navController: NavController = findNavController()
        val imageView = binding.imageViewRecipeImage
        var urlString = viewModel.recipe.imgUrl

        if(urlString == ""){
            urlString = "https://lh6.googleusercontent.com/proxy/V0UtHt8D7ZorPIVIFl-dMrihaZW-fpXlxCkE30bBCCAugVjuMwhMkC-Tg9UJiQ-ZmhQ8rr9qAgo3P91g9uu3o5250INWJtsbx-jzTWtKCVSsL-SR_gA=w1200-h630-p-k-no-nu"
        }
        //Dummy
        //var urlString: String = "https://i.ytimg.com/vi/uZfco9h0C_s/hqdefault.jpg"
        context?.let { Glide.with(it).load(urlString).into(imageView) }
        return binding.root
    }
    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //recieving the recipe name through bundle
        var recipeIDFromFragment = arguments?.let { RecipeDisplayFragmentArgs.fromBundle(it).recipeID }
        Toast.makeText(requireContext(), recipeIDFromFragment.toString(), Toast.LENGTH_SHORT).show()

        if (recipeIDFromFragment != null) {
            viewModel.getRecipeByID(recipeIDFromFragment)
        }else{
            Log.i(tag, "RecipeDisplayFragment Null ID")
        }

        //recieving the recipe name through bundle


       // Toast.makeText(requireContext(), viewModel.recipe.recipeId.toString(), Toast.LENGTH_SHORT).show()
    }

     */


}