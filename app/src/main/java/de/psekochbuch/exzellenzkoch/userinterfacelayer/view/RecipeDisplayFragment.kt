package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.util.Log
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


class RecipeDisplayFragment : Fragment(){
    var TAG = "RecipeDisplayFragment"

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

        Log.i(TAG, recipeID.toString().plus(" ist die ID im RecipeDisplayFragment"))
        viewModel.setRecipeByID(recipeID)



        //binding set to the according Fragment
        binding = RecipeDisplayFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.recipeDisplayViewModel = viewModel

        // set options Menu shown
        setHasOptionsMenu(true)

        //initialized navcontoller
        var navController: NavController = findNavController()



        viewModel.recipe.observe(this, Observer { recipe -> setImage(recipe.imgUrl)})
        viewModel.recipe.observe(this, Observer { recipe -> setTitle(recipe.title) })
        viewModel.recipe.observe(this, Observer { recipe -> setTimes(recipe.preparationTime, recipe.cookingTime) })
        viewModel.recipe.observe(this, Observer { recipe -> setTagsAndIngredietText(recipe.tags, recipe.ingredientsText) })
        viewModel.recipe.observe(this, Observer { recipe -> setCreationDate(recipe.creationTimeStamp)})



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu_display_recipe, menu)
    }
    fun setImage( urlString: String){
        var urlString = urlString
        //Toast.makeText(context, urlString, Toast.LENGTH_SHORT).show()

        if(urlString == "" || urlString.isNullOrBlank()||urlString.isNullOrEmpty()){
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"

        }
        val imageView = binding.imageViewRecipeImage

        context?.let { Glide.with(it).load(urlString).into(imageView) }

    }
    fun setTitle(title : String){
        binding.textViewRecipeTitle.text = title
    }
    fun setTimes(prepTime : Int, cookingTime : Int){
        binding.textViewRecipePrepTime.text = Integer.toString(prepTime)
        binding.textViewRecipeCookTime.text = Integer.toString(cookingTime)
    }
    fun setTagsAndIngredietText(tags : List<String>, ingredients : String){
        var tagString = ""
        for(tag in tagString){
            tagString.plus( " " + tag)
        }
        binding.textViewRecipeTags.text = tagString
        binding.textViewIngredientList.text = ingredients
    }
    fun setCreationDate(date : Date){
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateString = format.format(Date())
        val date = format.parse("2009-12-31")
        binding.textViewCreationTimestamp.text = dateString

    }


}