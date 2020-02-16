package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileEditFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileEditViewmodel

class ProfileEditFragment : Fragment(R.layout.profile_edit_fragment) {

    private lateinit var binding: ProfileEditFragmentBinding
    var viewModelTemp : ProfileEditViewmodel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewmodel recieved by Factory
        val viewModel : ProfileEditViewmodel by viewModels {
            InjectorUtils.provideProfileEditViewModelFactory(requireContext())
        }
        this.viewModelTemp = viewModel

        val userID =  arguments?.let { ProfileEditFragmentArgs.fromBundle(it).userID}
        Toast.makeText(context, userID, Toast.LENGTH_LONG).show()
        viewModel.setUserByID(userID!!)

        //binding set to the according Fragment
        binding = ProfileEditFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.profileEditViewmodel = viewModel
        //initialized navcontoller
        val navController: NavController = findNavController()

        viewModel.user.observe(this.viewLifecycleOwner, Observer {user->
            binding.textViewEnterUserID.setText(user.userId)
            binding.editTextUserDescription.setText(user.description)


            val imageView = binding.imageViewUserImg
            var urlString = user.imgUrl
            if(urlString == ""){
                urlString = "file:///android_asset/exampleimages/chef_avatar.png"
            }
            context?.let { Glide.with(it).load(urlString).into(imageView) }
        })





        binding.buttonChangeLoginData.setOnClickListener {

                //sending the userID to the ChangePW fragment
                navController.navigate(
                    ProfileEditFragmentDirections
                        .actionProfileEditFragmentToChangePasswordFragment()
                        .setUserID(userID)
                )
        }

        binding.buttonSaveProfileChanges.setOnClickListener {
            viewModel.save()
            navController.navigate(R.id.action_profileEditFragment_to_profileDisplayFragment)
        }
        binding.buttonDeleteProfile.setOnClickListener {
            Toast.makeText(requireContext(), "profil entfernt", Toast.LENGTH_SHORT).show()
            viewModel.deleteUser(userID!!)
            navController.navigate(R.id.action_profileEditFragment_to_registrationFragment)
        }
        binding.imageViewUserImg.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, ProfileEditFragment.PERMISSION_CODE);
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
    //Ab hier ist der Image Picker Code
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
            Toast.makeText(requireContext(),"neuer filepath ist $filePath",Toast.LENGTH_SHORT).show()
            viewModelTemp?.user?.value?.imgUrl = filePath

            //Shows image to the imageview which is provieded from the xml binding
            val imageView = binding.imageViewUserImg
            context?.let { Glide.with(it).load(returnIntent?.data).into(imageView) }
            imageView.setImageURI(returnIntent?.data)
        }
    }

}
