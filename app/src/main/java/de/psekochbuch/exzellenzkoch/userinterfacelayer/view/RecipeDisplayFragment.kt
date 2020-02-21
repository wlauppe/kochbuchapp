package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RecipeDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel
import java.text.SimpleDateFormat
import java.util.*

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class RecipeDisplayFragment : Fragment(){

    /**
     * The binding variable is used in the observe Methods, thus needs to be global.
     */
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

        //binding set to the according Fragment
        binding = RecipeDisplayFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.recipeDisplayViewModel = viewModel

        // set options Menu shown
        setHasOptionsMenu(true)

        binding.buttonReportRecipe.setOnClickListener{
            if (recipeID != null) {
                viewModel.reportRecipe(recipeID)
            }
        }

        binding.imageButtonFavourite.setOnClickListener{

            var navController: NavController = findNavController()
            navController.navigate(R.id.action_recipeDisplayFragment_to_favouriteFragment)
        }

        //initialized navcontoller
        var navController: NavController = findNavController()

        viewModel.recipe.observe(this, Observer { recipe -> setImage(recipe.imgUrl)})
        viewModel.recipe.observe(this, Observer { recipe -> setTitle(recipe.title) })
        viewModel.recipe.observe(this, Observer { recipe -> setTimes(recipe.preparationTime, recipe.cookingTime) })
        viewModel.recipe.observe(this, Observer { recipe -> setTagsAndIngredietText(recipe.tags, recipe.ingredientsText) })
        viewModel.recipe.observe(this, Observer { recipe ->
            binding.textViewRecipePrepDescription.text = recipe.preparation
            setCreationDate(recipe.creationTimeStamp)})



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu_display_recipe, menu)
    }

    /**
     * Update the image from defined URL via Glide as the Recipe
     * when the observable is changed in the ViewModel
     *
     * @param urlString the URL that defines the image for Glide
     */
    private fun setImage( urlString: String){
        var urlString = urlString
        if(urlString == "" || urlString.isNullOrBlank()||urlString.isNullOrEmpty()){
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        }
        val imageView = binding.imageViewRecipeImage
        context?.let { Glide.with(it).load(urlString).into(imageView) }

    }

    /**
     * Update the title via databinding when the observable is changed in the ViewModel
     *
     * @param title is the title of the recipe that needs to be updates
     */
    private fun setTitle(title : String){
        binding.textViewRecipeTitle.text = title
    }

    /**
     * Update the times via databinding when the observable is changed in the ViewModel
     *
     * @param prepTime is the preparationTime of the recipe that needs to be updates
     * @param cookingTime is the preparationTime of the recipe that needs to be updates
     */
    private fun setTimes(prepTime : Int, cookingTime : Int){
        binding.textViewRecipePrepTime.text = Integer.toString(prepTime)
        binding.textViewRecipeCookTime.text = Integer.toString(cookingTime)
    }

    /**
     * Update the tags via databinding when the observable is changed in the ViewModel
     *
     * @param tags are the set tags of the recipe that needs to be updates
     * @param ingredients is a String that displays the ingradients
     */
    private fun setTagsAndIngredietText(tags : List<String>, ingredients : String){
        val tagString = ""
        for(tag in tagString){
            tagString.plus( " " + tag)
        }
        binding.textViewRecipeTags.text = tagString
        binding.textViewIngredientList.text = ingredients
    }

    /**
     * Update the Date via databinding when the observable is changed in the ViewModel
     *
     * @param date is a the timestamp the recipe has
     */
    private fun setCreationDate(date : Date){
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateString = format.format(Date())
        val date = format.parse("2009-12-31")
        binding.textViewCreationTimestamp.text = dateString

    }


}