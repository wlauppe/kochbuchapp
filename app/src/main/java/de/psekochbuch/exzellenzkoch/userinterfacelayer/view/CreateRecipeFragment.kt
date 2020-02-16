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
import androidx.core.net.toFile
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel
import android.content.ContentResolver
import android.content.Context
import android.provider.OpenableColumns
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.create_recipe_fragment.view.*
import java.io.File

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class CreateRecipeFragment : Fragment() {

    /**
     * Binding attribute is needed in more than one class, thus used as global variable.
     */
    private lateinit var binding: CreateRecipeFragmentBinding

    /**
     * A ViewModel instance os needed for the observer methods
     */
    private var viewModelTemp : CreateRecipeViewmodel? = null

    //contentResolver = this.getActivity().getContentResolver()



    /**
     * Tag for the Logs (debugging purposes)
     */
    private var Tagg = "CreateRecipeFragment"

   
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // get the injected ViewModel
        val viewModel : CreateRecipeViewmodel by viewModels {
            InjectorUtils.provideCreateRecipeViewModelFactory(requireContext())
        }

        // initialize databinding variable and bind the ViewModel to xml
        binding = CreateRecipeFragmentBinding.inflate(inflater, container, false)
        binding.createRecipeViewModel = viewModel
        viewModelTemp = viewModel
        val context = getActivity()?.getApplicationContext()

        //SafeArgs provide the ID of the recipe that should be displayed
        val recipeID = arguments?.let { CreateRecipeFragmentArgs.fromBundle(it).recipeID }
        // if the recipeID isn't zero, fetch the recipe from the ViewModel to show its' values
        if(recipeID != null) {
           viewModel.setRecipeByID(recipeID)
        }

        // set up observer values for the ViewModel attributes (Logatgs are for debugging purposes)
        viewModel.recipe.observe(this, Observer { recipe -> setImage(recipe.imgUrl)
            setPreparation(recipe.preparation)
            setTitle(recipe.title)
            setTimes(recipe.preparationTime, recipe.cookingTime)
            setIngredientText(recipe.ingredientsText)
            setPortions(recipe.portions)
            setPublishedID(recipe.publishedRecipeId)
            getTagsFromRecipe(recipe.tags)
         })




        binding.checkBoxVeganCreateRecipeFragment.setOnClickListener{
        if(viewModel.tagCheckBoxVegan.value!!){
            viewModel.tagCheckBoxVegan.value = false
            binding.checkBoxVeganCreateRecipeFragment.isChecked = false
        }else {
            viewModel.tagCheckBoxVegan.value = true
            binding.checkBoxVeganCreateRecipeFragment.isChecked = true
        }
            Log.i(Tagg, viewModel.tagCheckBoxVegan.value.toString().plus( " = vm ; ").plus( binding.checkBoxVeganCreateRecipeFragment.isChecked.toString().plus(" = binding")))

        }
        binding.checkBoxVegetarianCreateRecipeFragment.setOnClickListener{
        if(viewModel.tagCheckBoxVegetarian.value!!){
            viewModel.tagCheckBoxVegetarian.value = false
            binding.checkBoxVegetarianCreateRecipeFragment.isChecked = false
        }else{
            viewModel.tagCheckBoxVegetarian.value = true
            binding.checkBoxVegetarianCreateRecipeFragment.isChecked = true
        }
        }
        binding.checkBoxHeartyCreateRecipeFragment.setOnClickListener{
            if(viewModel.tagCheckBoxSavoury.value!!){
                viewModel.tagCheckBoxSavoury.value = false
                binding.checkBoxHeartyCreateRecipeFragment.isChecked = false
            }else{
                viewModel.tagCheckBoxSavoury.value = true
                binding.checkBoxHeartyCreateRecipeFragment.isChecked = true
            }
        }
        binding.checkBoxSweetCreateRecipeFragment.setOnClickListener{
            if(viewModel.tagCheckBoxSweet.value!!){
                viewModel.tagCheckBoxSweet.value = false
                binding.checkBoxSweetCreateRecipeFragment.isChecked = false
            }else{
                viewModel.tagCheckBoxSweet.value = true
                binding.checkBoxSweetCreateRecipeFragment.isChecked = true
            }
        }
        binding.checkBoxSaltyCreateRecipeFragment.setOnClickListener{
            if(viewModel.tagCheckBoxSalty.value!!){
                viewModel.tagCheckBoxSalty.value = false
                binding.checkBoxSaltyCreateRecipeFragment.isChecked = false
            }else{
                viewModel.tagCheckBoxSalty.value = true
                binding.checkBoxSaltyCreateRecipeFragment.isChecked = true
            }
        }
        binding.checkBoxCheap.setOnClickListener{
            if(viewModel.tagCheckBoxCheap.value!!){
                viewModel.tagCheckBoxCheap.value = false
                binding.checkBoxCheap.isChecked = false
            }else{
                viewModel.tagCheckBoxCheap.value = true
                binding.checkBoxCheap.isChecked = true
            }
        }
        binding.checkBoxPublishCreateRecipeFragment.setOnClickListener() {
            view -> if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            if (checked) {
                viewModel.tagCheckBoxPublish.value = true
                viewModel.publishRecipe()
                if (!checked) {
                    viewModel.tagCheckBoxPublish.value = false
                    viewModel.dontPublishRecipe()
                }


            }
            }
        }








        //initialize navcontoller
        val navController: NavController = findNavController()



        //Snackbar, die Fehlermeldungen anzeigt.
        viewModel.showSnackBarEvent.observe(this, Observer {
            if (it == true) { // Observed state is true.
                Snackbar.make(
                    view!!,
                    //getString(R.string.cleared_message),
                    viewModel.snackbarMessage.value!!,
                    //viewModel.errorString.value.toString(),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                viewModel.doneShowingSnackbar()
            }

        })



       // Snackbar.make(view!!, viewModel.errorString.value.toString(), Snackbar.LENGTH_SHORT).show()


        // logic for the "Save recipe"-button
        binding.buttonCreateRecipeAndGotoRecipeList.setOnClickListener {

            //Toast.makeText(requireContext(),"Rezept zur Rezeptliste hinzugefügt",Toast.LENGTH_SHORT).show()
            viewModel.saveRecipe(requireContext())
           //TODO wieder reinmachen ist nur temporär draußen navController.navigate(R.id.action_createRecipeFragment_to_recipeListFragment)
        }

        //Image intent
        /*binding.imageButtonRecipeImage.setOnClickListener{
          var imgUrl =  viewModel.getImage()
        }
        */

        // logic for the image button to load a recipe image
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


    /**
     * Create an intent to be able to share recipes for future features.
     */
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






    override fun onActivityResult(requestCode: Int, resultCode: Int, returnIntent: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            val contentResolver = getActivity()?.getApplicationContext()?.contentResolver

            val returnUri = returnIntent?.data
            val result: String?

            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = activity?.contentResolver?.query(returnUri!!, filePathColumn,null,null,null)!!
            cursor.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val filePath = cursor.getString(columnIndex)
            cursor.close()

            //val file = File(returnUri?.path)
            //viewModelTemp?.imgUrl?.value = returnUri?.toFile()?.absolutePath
            viewModelTemp?.imgUrl?.value = filePath

            /* returnUri?.let {
                if (returnUri.scheme.equals("content")) {
                    val cursor = contentResolver?.query(returnUri, null, null, null, null);
                    try {
                        if (cursor != null && cursor.moveToFirst()) {
                            viewModelTemp?.imgUrl?.value =
                                cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        }
                    } finally {
                        cursor?.close()
                    }
                }
            } */



                //Speichere neue IMGURL
                //val uri=data?.data
                //val file = uri?.toFile()
                //val path=file?.absolutePath

                // viewModelTemp?.imgUrl?.value = result

                //Zeige Bild an.
                val imageView = binding.imageButtonRecipeImage
                context?.let { Glide.with(it).load(returnIntent?.data).into(imageView) }
                imageView.setImageURI(returnIntent?.data)

                // = data?.data.toString()
                //imageView.setImageURI(data?.data)
                //data?.data?.path
            }

    }

    /**
     * Set the image string of the observed recipe via binding and Glide logic.
     *
     * @param img is the imageURL with which Glide can display the image
     */
    fun setImage(img : String){
        //binding viewmodel with xml components
        val imageView = binding.imageButtonRecipeImage
        var urlString = viewModelTemp!!.recipe.value?.imgUrl
        if(urlString.isNullOrBlank()){
            urlString = "file:///android_asset/exampleimages/vegetables_lowcontrast.png"
        }
        context?.let { Glide.with(it).load(urlString).into(imageView) }


    }

    /**
     * Set the title of the observed recipe.
     *
     * @param title
     */
    fun setTitle(title: String){
        binding.editTextRecipeTitleCreateRecipeFragment.setText(title)

    }

    /**
     * Set the ingredient field of the observed recipe by binding.
     *
     * @param ingredients is a String of ingredients the recipe has
     */
    fun setIngredientText(ingredients : String){
        binding.editTextIngredientsCreateRecipeFragment.setText(ingredients)
    }
    /**
     * Set the preparation field of the observed recipe by binding.
     *
     * @param preparation is a String of the preparation description, which the recipe has
     */
    fun setPreparation(preparation: String){
        binding.editTextPreparationDescriptionCreateRecipeFragment.setText(preparation)

    }

    /**
     * Set the two times of the observed recipe:
     * preparation and cooking time.
     *
     * @param prepTime is the preparationTime the recipe should have
     * @param cookTime is the cookingTme the recipe should have
     */
    fun setTimes(prepTime : Int, cookTime: Int){
        val prepTimeString = Integer.toString(prepTime)
        val cookTimeString = Integer.toString(cookTime)
        binding.editTextPreparingTimeCreateRecipeFragment.setText(prepTimeString)
        binding.editTextCookingTimeCreateRecipeFragment.setText(cookTimeString)
    }

    /**
     * This method sets the portion size of the observed recipe by binding the parameter value
     * to a (to be implemented) textField that will show the value.
     *
     * @param portions is the amount of portions this recipe will have
     */
    fun setPortions(portions: Int){
        TODO()
    }

    /**
     * This method sets the setPublishedID of the observed recipe.
     * If the given recipe is already published, its ID is not null and can be reset
     * to the recipe's publishedID.
     *
     * @param publishedID is null, if the recipe hasn't been published
     */
    fun setPublishedID(publishedID : Int?){
        if(publishedID != null){
            //noch nicht gepublished
            viewModelTemp?.publishedID = publishedID
        }
    }


    fun getTagsFromRecipe(tags : List<String>){
        if(tags.contains("vegan")){
            binding.checkBoxVeganCreateRecipeFragment.isChecked = true
        }
        if(tags.contains("vegetarisch")){
            viewModelTemp?.tagCheckBoxVegetarian = MutableLiveData(true)
        }
        if(tags.contains("salzig")){
            viewModelTemp?.tagCheckBoxSalty   = MutableLiveData(true)
        }
        if(tags.contains("sweet")){
            viewModelTemp?.tagCheckBoxSweet   = MutableLiveData(true)
        }
        if(tags.contains("günstig")){
            viewModelTemp?.tagCheckBoxCheap   = MutableLiveData(true)
        }
        if(tags.contains("herzhaft")){
            viewModelTemp?.tagCheckBoxSavoury   = MutableLiveData(true)
        }
    }



}