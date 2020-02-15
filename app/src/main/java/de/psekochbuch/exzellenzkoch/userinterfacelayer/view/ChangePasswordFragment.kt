package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ChangePasswordFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewModel

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class ChangePasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // provide ViewModel
        val viewModel : ChangePasswordViewModel by viewModels {
            InjectorUtils.provideChangePasswordViewModelFactory(requireContext())
        }

        // TODO doc
        val userID = arguments?.let {ChangePasswordFragmentArgs.fromBundle(it).userID }
        if (userID != null) {
            viewModel!!.setUserById(userID)
        }else{
            Log.i("ChangepasswordViewModel", "Passed UserID == Null !!!")
        }

        // binding set to the according Fragment
        val  binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)
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


