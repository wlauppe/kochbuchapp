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
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayFragment : Fragment() {
    private lateinit var binding: ProfileDisplayFragmentBinding
    private lateinit var viewModel: ProfileDisplayViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = ProfileDisplayFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(ProfileDisplayViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.profileDisplayViewmodel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()

        binding.buttonReportUser.setOnClickListener {
            Toast.makeText(requireContext(), "nutzer gemeldet", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        }
        binding.buttonEditProfile.setOnClickListener {
            //if the user wants to edit a profile which is not his he is not allowed
            if (viewModel.isOwner()) {
                navController.navigate((R.id.action_profileDisplayFragment_to_profileEditFragment))
            }
        }
        return binding.root
    }


}