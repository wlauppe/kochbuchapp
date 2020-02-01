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
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewmodel
import kotlinx.android.synthetic.main.change_password_fragment.view.*

class ChangePasswordFragment : Fragment() {

    private lateinit var navController:NavController
    private lateinit var binding : ChangePasswordFragmentBinding
    private lateinit var viewModel:ChangePasswordViewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    //binding
        val binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)

        //Viewmodel
            viewModel = ViewModelProvider(this).get(ChangePasswordViewmodel::class.java)
            binding.changePasswordViewModel = viewModel

        //navcontroller
        navController = findNavController()
        binding.buttonChangePassword.setOnClickListener{
           // navController.navigate(R.id.action_login_fragment_to_profile_display_fragment)
        }


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


