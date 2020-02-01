package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ChangePasswordFragmentBinding
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewmodel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewmodel
import kotlinx.android.synthetic.main.change_password_fragment.view.*

class ChangePasswordFragment : Fragment() {

    private lateinit var binding: ChangePasswordFragmentBinding
    private lateinit var viewModel : ChangePasswordViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(ChangePasswordViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.changePasswordViewModel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()


        binding.buttonChangePassword.setOnClickListener{
            //Change PW
            viewModel.changePassword()
            //navigate to Profile display Fragment
            navController.navigate(R.id.action_changePasswordFragment_to_profileDisplayFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


