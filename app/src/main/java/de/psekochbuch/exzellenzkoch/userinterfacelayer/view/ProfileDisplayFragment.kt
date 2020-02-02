package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.ProfileDisplayAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayFragment : Fragment() {

    private lateinit var binding: ProfileDisplayFragmentBinding
    private lateinit var viewModel: ProfileDisplayViewmodel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding set to the according Fragment
        binding = ProfileDisplayFragmentBinding.inflate(inflater, container, false)
        //viewmodel recieved by viewmodelproviders
        viewModel = ViewModelProvider(this).get(ProfileDisplayViewmodel::class.java)
        binding.profileDisplayRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        val adapter = ProfileDisplayAdapter(viewModel)
        binding.profileDisplayRecyclerView.adapter = adapter
        //Add an observer pattern to the Fragment. The Owner is the fragment and the observer are the recipes
        val observer = Observer<List<PublicRecipe>> { items ->
            adapter.setNewItems(items)
        }
        viewModel.recipes.observe(this, observer)
        //Sets according viewmodel from XML to this fragment
        binding.profileDisplayViewmodel = viewModel
        //initialized navcontoller
        var navController: NavController = findNavController()
        binding.buttonProfileDisplayFragmentFlagUser.setOnClickListener {
            Toast.makeText(requireContext(), "Nutzer gemeldet", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.action_profileDisplayFragment_to_publicRecipeSearchFragment)
        }
        binding.buttonProfileDisplayFragmentEditProfile.setOnClickListener {

            if (!viewModel.isOwner()) {
                Toast.makeText(
                    requireContext(),
                    "nur das eigene Profil kann bearbeitet werden",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                navController.navigate(R.id.action_profileDisplayFragment_to_profileEditFragment)
            }
        }






        return binding.root
    }

}