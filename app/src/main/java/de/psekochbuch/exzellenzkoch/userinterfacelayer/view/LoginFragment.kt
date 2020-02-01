package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.view.SupportMenuInflater
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewmodel

class LoginFragment: Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    //private lateinit var binding : LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //initializing the Binding
            val binding = LoginFragmentBinding.inflate(inflater, container, false)

        //navcontroller
        val navController = findNavController()

        //setOnClicklistener
        binding.buttonLogin.setOnClickListener{
           // navController.navigate(R.id.action_login_fragment_to_profile_display_fragment)
        }

        //initializing the Viewmodel
            val viewModel = ViewModelProvider(this).get(LoginViewmodel::class.java)

        //connecting binding with viewmodel
            binding.loginviewModel = viewModel


        return binding.root
    }

}