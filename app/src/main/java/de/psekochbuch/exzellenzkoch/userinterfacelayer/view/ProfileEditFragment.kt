package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileEditFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileEditViewmodel

class ProfileEditFragment : Fragment() {

    private lateinit var binding: ProfileEditFragmentBinding
    private lateinit var viewModel: ProfileEditViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(ProfileEditViewmodel::class.java)

        var userID =  arguments?.let { ProfileEditFragmentArgs.fromBundle(it).userID}
        Toast.makeText(context, userID, Toast.LENGTH_LONG).show()
        viewModel.setUserByID(userID!!)

        //binding set to the according Fragment
        binding = ProfileEditFragmentBinding.inflate(inflater, container, false)

        //Sets according viewmodel from XML to this fragment
        binding.profileEditViewmodel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()

        val imageView = binding.imageViewUserImg
        var urlString = viewModel.userImgURL.value
        context?.let { Glide.with(it).load(urlString).into(imageView) }


        binding.buttonChangeLoginData.setOnClickListener {

                //sending the userID to the ChangePW fragment
                navController!!.navigate(
                    AdminFragmentDirections
                        .actionAdminFragmentToProfileDisplayFragment()
                        .setUserID(userID)
                )
        }

        binding.buttonSaveProfileChanges.setOnClickListener {
            navController.navigate(R.id.action_profileEditFragment_to_profileDisplayFragment)
        }
        binding.buttonDeleteProfile.setOnClickListener {
            Toast.makeText(requireContext(), "profil entfernt", Toast.LENGTH_SHORT).show()
            viewModel.deleteUser(userID!!)
            navController.navigate(R.id.action_profileEditFragment_to_registrationFragment)
        }
        return binding.root
    }

}