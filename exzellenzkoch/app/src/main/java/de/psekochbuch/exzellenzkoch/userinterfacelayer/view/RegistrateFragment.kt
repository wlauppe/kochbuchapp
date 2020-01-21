package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.RegistrationFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RegistrateViewModel

class RegistrateFragment : Fragment(R.layout.registration_fragment) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = RegistrationFragmentBinding.inflate(inflater,container,false)

        val ac = activity
        if(ac != null) {
            val viewModel = ViewModelProviders.of(ac).get(RegistrateViewModel::class.java)
            binding.viewModel = viewModel
            binding.lifecycleOwner = ac
        }

        return binding.root
    }
}