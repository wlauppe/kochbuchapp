package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import de.psekochbuch.exzellenzkoch.databinding.LoginFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.LoginViewmodel

class LoginFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)

        val ac = activity
        if(ac != null){
            val viewModel = ViewModelProviders.of(ac).get(LoginViewmodel::class.java)
            binding.loginviewModel = viewModel
            binding.lifecycleOwner = ac
        }
        return binding.root
    }
}