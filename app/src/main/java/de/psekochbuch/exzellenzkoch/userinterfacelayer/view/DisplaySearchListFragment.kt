package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListFragment: Fragment() {

    private lateinit var binding: DisplaySearchlistFragmentBinding

    private lateinit var viewModel : DisplaySearchListViewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //binding set to the according Fragment
        binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(DisplaySearchListViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.displaySearchListViewmodel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()
        //buttons with fragment change connected with buttonlisteners here

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}