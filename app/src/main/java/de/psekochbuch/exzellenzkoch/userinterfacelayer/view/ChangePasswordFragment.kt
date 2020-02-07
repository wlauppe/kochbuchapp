package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ChangePasswordFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewmodel

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: ChangePasswordFragmentBinding
    //val viewModel : ChangePasswordViewmodel by viewModels {
    //    InjectorUtils.provideChangePasswordViewModelFactory(requireContext())
    //}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewmodel
        val viewModel : ChangePasswordViewmodel by viewModels {
            InjectorUtils.provideChangePasswordViewModelFactory(requireContext())
        }
        //binding set to the according Fragment
        binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        //viewModel = ViewModelProvider(this).get(ChangePasswordViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.changePasswordViewModel = viewModel
        //initialized navcontoller
        val navController: NavController = findNavController()


        binding.buttonChangePassword.setOnClickListener {
            //Change PW
            viewModel.changePassword()
            //navigate to Profile display Fragment
            navController.navigate(R.id.action_changePasswordFragment_to_profileDisplayFragment)
        }

        return binding.root
    }

}


