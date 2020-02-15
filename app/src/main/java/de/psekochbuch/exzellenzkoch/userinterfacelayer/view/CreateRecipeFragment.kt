package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.R.attr.bitmap
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
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
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.CreateRecipeFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.CreateRecipeViewmodel

import android.os.Build.*
import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class CreateRecipeFragment : Fragment() {


    var Tagg = "CreateRecipeFragment"


    /**
     * Binding attribute is needed in more than one class, thus used as global variable
     */
    private lateinit var binding: CreateRecipeFragmentBinding


    var viewModelTemp : CreateRecipeViewmodel? = null

    //Methods

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel : CreateRecipeViewmodel by viewModels {
            InjectorUtils.provideCreateRecipeViewModelFactory(requireContext())
        }

        //binding set to the according Fragment
        binding = CreateRecipeFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        //Sets according viewmodel from XML to this fragment
        binding.createRecipeViewModel = viewModel
        viewModelTemp = viewModel

        //SafeArgs---------------------------
        val recipeID = arguments?.let { CreateRecipeFragmentArgs.fromBundle(it).recipeID }

        if(recipeID != null) {
           viewModel.setRecipeByID(recipeID)


        }


        viewModel.recipe.observe(this, Observer { recipe -> setImage(recipe.imgUrl)
        Log.i(Tagg, recipe.imgUrl.plus(" ist die Image URL"))})
        viewModel.recipe.observe(this, Observer { recipe -> setTitle(recipe.title)
            Log.i(Tagg, recipe.title.plus(" ist der Title"))})

        viewModel.recipe.observe(this, Observer { recipe -> setTimes(recipe.preparationTime, recipe.cookingTime)})
        viewModel.recipe.observe(this, Observer { recipe -> setIngredientText(recipe.ingredientsText)

            Log.i(Tagg, recipe.ingredientsText.plus(" ingredients"))})
        viewModel.recipe.observe(this, Observer { recipe -> setPortions(recipe.portions)})

        viewModel.recipe.observe(this, Observer { recipe -> setPublishedID(recipe.publishedRecipeId)})

        viewModel.recipe.observe(this, Observer { recipe -> setPublishedID(recipe.publishedRecipeId)})



        //initialized navcontoller
        val navController: NavController = findNavController()


        binding.buttonCreateRecipeAndGotoRecipeList.setOnClickListener {
            //Create Recipe
           // Toast.makeText(requireContext(),"Rezept zur Rezeptliste hinzugefügt",Toast.LENGTH_SHORT).show()
            viewModel.saveRecipe(requireContext())
            navController.navigate(R.id.action_createRecipeFragment_to_recipeListFragment)
        }

        //Image intent
        /*binding.imageButtonRecipeImage.setOnClickListener{
          var imgUrl =  viewModel.getImage()
        }
        */

        //Image button click
        binding.imageButtonRecipeImage.setOnClickListener {
            //check runtime permission
            if (VERSION.SDK_INT >= VERSION_CODES.M){
                if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        return binding.root
    }


    // Creating our Share Intent
    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //TODO image müsste hier noch wieder gespeichert und an Glide übergeben werden.

            val imageView = binding.imageButtonRecipeImage
            context?.let{Glide.with(it).load(data?.data).into(imageView)}
            imageView.setImageURI(data?.data)
            // = data?.data.toString()
           //imageView.setImageURI(data?.data)
        }
    }

    fun setImage(img : String){
        //binding viewmodel with xml components
        val imageView = binding.imageButtonRecipeImage
        var urlString = viewModelTemp!!.recipe.value?.imgUrl
        if(urlString.isNullOrBlank()){
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        }
        context?.let { Glide.with(it).load(urlString).into(imageView) }


    }
    fun setTitle(title: String){
        binding.editTextRecipeTitleCreateRecipeFragment.setText(title)

    }
    fun setIngredientText( ingredients : String){
        binding.editTextIngredientsCreateRecipeFragment.setText(ingredients)
    }


    fun setTimes(prepTime : Int, cookTime: Int){
        var prepTimeString = Integer.toString(prepTime)
        var cookTimeString = Integer.toString(cookTime)
        binding.editTextPreparingTimeCreateRecipeFragment.setText(prepTimeString)
        binding.editTextCookingTimeCreateRecipeFragment.setText(cookTimeString)
    }
    fun setPortions(portions: Int){
//TODO
    }
    fun setPublishedID(publishedID : Int?){
        if(publishedID != null){
            //noch nicht gepublished
            viewModelTemp?.publishedID = publishedID
        }
    }

}