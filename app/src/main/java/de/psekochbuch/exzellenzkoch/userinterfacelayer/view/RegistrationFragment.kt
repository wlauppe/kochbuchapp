package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RegistrationFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
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

            viewModel.focusable.postValue(true)
            binding.llProgressBar.visibility = View.VISIBLE

            viewModel.registerOnClick { userId, result, message ->

                if(result == AuthenticationResult.REGISTRATIONSUCCESS) {
                    if (userId != null && userId != "") {
                        viewModel.progressBarVisibility.postValue(false)
                        navController.navigate(
                            RegistrationFragmentDirections.actionRegistrationFragmentToProfileEditFragment().setUserID(
                                userId
                            )
                        )
                    }
                } else
                {
                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                }

                binding.llProgressBar.visibility = View.INVISIBLE
            }

        }

        return binding.root
    }


}