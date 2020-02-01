package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.SupportMenuInflater
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.databinding.PublicRecipeSearchFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.PublicRecipeSearchViewmodel

class LoginFragment: Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel : LoginViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(LoginViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.loginviewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()
        binding.buttonLogin.setOnClickListener{
            navController.navigate(R.id.action_loginFragment_to_profileDisplayFragment)
        }
        binding.buttonRegister.setOnClickListener{
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}