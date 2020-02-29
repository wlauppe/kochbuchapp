package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
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

        checkIsLogin(navController)

        binding.buttonLoginFragmentLogin.setOnClickListener {
            setLoadingScreen(false)
            val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
            if(isConnected) {
                viewModel.login { userId, result, message ->
                    if (userId != "" && result == AuthenticationResult.LOGINSUCCESS) {
                        AuthentificationImpl.getToken(true) {
                            if (it != null && it != "") {
                                InjectorUtils.setToken(it)
                            }
                        }
                        setLoadingScreen(true)
                        navController.navigate(
                            LoginFragmentDirections.actionLoginFragmentToProfileDisplayFragment().setUserID(
                                userId
                            )
                        )


                    } else {
                        setLoadingScreen(true)
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                setLoadingScreen(true)
                Toast.makeText(context, "No connection to the internet", Toast.LENGTH_SHORT).show()
            }

        }
        binding.buttonLoginFragmentRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        return binding.root
    }

    private fun checkIsLogin(navController: NavController) {
        if(AuthentificationImpl.isLogIn())
        {
            AuthentificationImpl.getToken(false){
                if(it != null && it != "") {
                    InjectorUtils.setToken(it)
                }
            }
            val userId = AuthentificationImpl.getUserId()
            navController.navigate(
                LoginFragmentDirections.actionLoginFragmentToProfileDisplayFragment().setUserID(
                    userId))
        }
    }

    private fun setLoadingScreen(state: Boolean) {

        binding.logInProgressbar.visibility = if(state) { View.INVISIBLE }
        else View.VISIBLE



        binding.buttonLoginFragmentLogin.isClickable = state
        binding.buttonLoginFragmentRegister.isClickable = state
        binding.buttonLoginFragmentLogin.visibility = if(!state) { View.INVISIBLE }
        else View.VISIBLE
        binding.buttonLoginFragmentRegister.visibility = if(!state) { View.INVISIBLE }
        else View.VISIBLE
        binding.editTextLoginFragmentEmail.isEnabled = state
        binding.editTextLoginFragmentPassword.isEnabled = state
    }
}