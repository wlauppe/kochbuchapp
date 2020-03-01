package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.content.Context
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
import de.psekochbuch.exzellenzkoch.databinding.RegistrationFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RegistrationViewModel

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 */
class RegistrationFragment : Fragment(R.layout.registration_fragment) {

    /**
     * The binding variable is needed in another Method and therefore needs to be global
     */
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

        val navController: NavController = findNavController()
        binding.buttonRegisterFragmentRegister.setOnClickListener {

            EspressoIdlingResource.increment()


            setLoadingScreen(false)
            viewModel.registerOnClick { userId, result, message ->
                if(result == AuthenticationResult.REGISTRATIONSUCCESS) {
                    if (userId != null && userId != "") {
                        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                        with (sharedPref?.edit()) {
                            this?.putBoolean("Verified",true)
                            this?.commit()
                        }
                        AuthentificationImpl.getToken(true) {
                            if(it != null && it != "") {
                                InjectorUtils.setToken(it)
                            }
                        }
                        setLoadingScreen(true)
                        navController.navigate(
                            RegistrationFragmentDirections.actionRegistrationFragmentToProfileEditFragment().setUserID(
                                userId
                            )
                        )
                    }
                } else if (result == AuthenticationResult.USERNOTSERVERCREATED) {
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    with (sharedPref?.edit()) {
                        this?.putBoolean("Verified",false)
                        this?.commit()
                    }
                    AuthentificationImpl.getToken(true) {
                        if(it != null && it != "") {
                            InjectorUtils.setToken(it)
                        }
                    }
                    setLoadingScreen(true)
                    navController.navigate(
                        RegistrationFragmentDirections.actionRegistrationFragmentToProfileEditFragment().setUserID(
                            ""
                        )
                    )
                }
                else if (result == AuthenticationResult.REGISTRATIONFAILED || result == AuthenticationResult.USERALREADYEXIST)
                {
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                } else if (result == AuthenticationResult.USERIDNOTSET)
                {
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    with (sharedPref?.edit()) {
                        this?.putBoolean("Verified",false)
                        this?.commit()
                    }
                }

                setLoadingScreen(true)
                EspressoIdlingResource.decrement()
            }

        }

        return binding.root
    }

    /**
     * This function displays a progress bar if the screen loads
     *
     * @param state
     */
    private fun setLoadingScreen(state: Boolean) {
        binding.llProgressBar.visibility = if(state) { View.INVISIBLE }
            else View.VISIBLE

        binding.buttonRegisterFragmentRegister.isClickable = state
        binding.editTextRegisterEmailInput.isEnabled = state
        binding.editTextRegisterPasswordInput.isEnabled = state
        binding.editTextRegisterUsernidInput.isEnabled = state
    }


}