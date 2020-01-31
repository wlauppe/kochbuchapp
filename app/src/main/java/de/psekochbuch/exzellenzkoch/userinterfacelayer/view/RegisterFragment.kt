package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RegistrationFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RegisterViewModel

class RegisterFragment : Fragment(R.layout.registration_fragment) {

    private lateinit var navController:NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val binding = RegistrationFragmentBinding.inflate(inflater,container,false)

<<<<<<< HEAD:app/src/main/java/de/psekochbuch/exzellenzkoch/userinterfacelayer/view/RegisterFragment.kt
        val ac = activity
        if(ac != null) {
            val viewModel = ViewModelProviders.of(ac).get(RegisterViewModel::class.java)
=======

        navController = findNavController()


        val viewModel = ViewModelProvider(this).get(RegistrateViewModel::class.java)
>>>>>>> 68145c5a9a5b8987c35472efd54c5358497fdf0f:app/src/main/java/de/psekochbuch/exzellenzkoch/userinterfacelayer/view/RegistrateFragment.kt
            binding.viewModel = viewModel





        return binding.root
    }
}