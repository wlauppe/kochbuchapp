package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewModel

class LoginFragment : Fragment(R.layout.login_fragment) {

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
            setLoadingScreen(false)
            viewModel.login{ userId, result, message ->
                if(userId != "" && result == AuthenticationResult.LOGINSUCCESS) {
                    setLoadingScreen(true)
                    navController.navigate(
                        LoginFragmentDirections.actionLoginFragmentToProfileDisplayFragment().setUserID(
                            userId
                        )
                    )
                }
                else {
                    setLoadingScreen(true)
                    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
                }
            }

        }
        binding.buttonLoginFragmentRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        return binding.root
    }

    private fun setLoadingScreen(state: Boolean) {

        binding.logInProgressbar.visibility = if(state) { View.INVISIBLE }
        else View.VISIBLE

        /*binding..visibility = if(state) { View.INVISIBLE }
        else View.VISIBLE*/

        binding.buttonLoginFragmentLogin.isClickable = state
        binding.buttonLoginFragmentRegister.isClickable = state
        binding.editTextLoginFragmentEmail.isEnabled = state
        binding.editTextLoginFragmentPassword.isEnabled = state
    }
}