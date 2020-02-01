package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RegistrationFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment(R.layout.registration_fragment) {

    private lateinit var navController:NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val binding = RegistrationFragmentBinding.inflate(inflater,container,false)



        navController = findNavController()


        val viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

            binding.viewModel = viewModel





        return binding.root
    }
}