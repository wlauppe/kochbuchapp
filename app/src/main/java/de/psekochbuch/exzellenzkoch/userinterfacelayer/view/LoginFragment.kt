package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        val viewModel : LoginViewModel by viewModels {
            InjectorUtils.provideLoginViewModelFactory(requireContext())
        }

        //Sets according viewmodel from XML to this fragment
        binding.loginviewModel = viewModel
        //initialized navcontoller
        val navController: NavController = findNavController()
        binding.buttonLoginFragmentLogin.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToProfileDisplayFragment().setUserID("Udo"))
        }
        binding.buttonLoginFragmentRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        return binding.root
    }

}