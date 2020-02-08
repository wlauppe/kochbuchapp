package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.ProfileDisplayAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //ViewModel
        val viewModel : ProfileDisplayViewmodel by viewModels {
            InjectorUtils.provideProfileDisplayViewModelFactory(requireContext())
        }
        //SafeArge--------------------------------
        var userID = arguments?.let { ProfileDisplayFragmentArgs.fromBundle(it).userID }
        viewModel.setUserByID(userID!!)
        Toast.makeText(requireContext(), userID.toString(), Toast.LENGTH_SHORT).show()

        val binding = ProfileDisplayFragmentBinding.inflate(inflater, container, false)
        binding.profileDisplayViewmodel = viewModel
        // init binding variable
        binding.profileDisplayRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // set input values to show them in the xml
        val listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!
        val exampleAdapter = ProfileDisplayAdapter(listOfRecipeNames,viewModel, requireContext())

        //Adapter--------------------------------------------
        binding.profileDisplayRecyclerView.adapter = exampleAdapter
             // set observer
             val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
             }
             viewModel.recipes.observe(this.viewLifecycleOwner, observer)
            binding.profileDisplayRecyclerView.setHasFixedSize(true)
        //Glide------------------------------------------------
        binding.textViewProfileDisplayDescription.text = viewModel.userDesc

        binding.textViewProfileDisplayFragmentTitle.text = viewModel.userID
        val imageView = binding.imageView2
        var urlString = viewModel.userImg
        context?.let { Glide.with(it).load(urlString).into(imageView) }

        binding.buttonProfileDisplayFragmentEditProfile.setOnClickListener{
            val navController = findNavController()
            navController.navigate(ProfileDisplayFragmentDirections.actionProfileDisplayFragmentToProfileEditFragment().setUserID(userID))
        }

        binding.buttonProfileDisplayFragmentFlagUser.setOnClickListener{
            viewModel.flagUserById()
        }




        return binding.root
    }





}