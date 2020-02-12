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
import de.psekochbuch.exzellenzkoch.databinding.RegistrationFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment(R.layout.registration_fragment) {

    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        //viewmodel recieved by Factory
        val viewModel : RegistrationViewModel by viewModels {
            InjectorUtils.provideRegistrationViewModelFactory(requireContext())
        }

        //Sets according viewmodel from XML to this fragment
        binding.viewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()
        binding.buttonRegisterFragmentRegister.setOnClickListener {
            viewModel.registerOnClick()

            // TODO useless var??
            var userID = "Udo"
            navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToProfileEditFragment().setUserID(userID))
        }


        return binding.root
    }
}