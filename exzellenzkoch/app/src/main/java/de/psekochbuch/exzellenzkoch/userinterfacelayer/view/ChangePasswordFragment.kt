package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import de.psekochbuch.exzellenzkoch.databinding.ChangePasswordFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewmodel

class ChangePasswordFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)

            val viewModel = ViewModelProviders.of(this).get(ChangePasswordViewmodel::class.java)
            binding.changePasswordViewModel = viewModel
            binding.lifecycleOwner = this


        //val navController = findNavController()

        return binding.root
    }
}