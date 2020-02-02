package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayFragment : Fragment() {
    private lateinit var binding: ProfileDisplayFragmentBinding
    private lateinit var viewModel: ProfileDisplayViewmodel
    private lateinit var  navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = ProfileDisplayFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(ProfileDisplayViewmodel::class.java)
        //Sets according viewmodel from XML to this fragment
        binding.profileDisplayViewmodel = viewModel
        //initialized navcontoller
        navController = findNavController()


        binding.buttonProfileDisplayFragmentFlagUser.setOnClickListener {
            Toast.makeText(requireContext(), "Nutzer gemeldet", Toast.LENGTH_SHORT).show()

            navController.navigateUp()
        }
        binding.buttonProfileDisplayFragmentEditProfile.setOnClickListener {
            //if the user wants to edit a profile which is not his he is not allowed
            if (viewModel.isOwner()) {
                navController.navigate((R.id.action_profileDisplayFragment_to_profileEditFragment))
            }

            class ProfileDisplayFragment : Fragment(), View.OnClickListener {
                var navController: NavController? = null

                var profileDisplayViewmodel: ProfileDisplayViewmodel? = null


                override fun onCreateView(
                    inflater: LayoutInflater,
                    container: ViewGroup?,
                    savedInstanceState: Bundle?
                ): View? {
                    var view: View =
                        inflater.inflate(R.layout.profile_display_fragment, container, false)

                    profileDisplayViewmodel =
                        ViewModelProvider(this).get(ProfileDisplayViewmodel::class.java)

                    return view

                }

                /**
                override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                    super.onViewCreated(view, savedInstanceState)
                    navController = Navigation.findNavController(view)
                    view.findViewById<Button>(R.id.goto_recipe_button).setOnClickListener(this)
                }
                */

                override fun onClick(v: View?) {
                    //when (v!!.id) {
                    //    R.id.goto_recipe_button -> navController!!.navigate(R.id.profile_display_fragment_to_recipe_display_fragment)
                    //}

                }


            }
        }
        return binding.root
    }
}