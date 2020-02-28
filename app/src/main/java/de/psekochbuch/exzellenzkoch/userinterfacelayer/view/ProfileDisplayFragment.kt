package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.ProfileDisplayAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

/**
* The Fragment class provides logic for binding the respective .xml layout file to the class
* and calls functions from the underlying ViewModel.
* The ViewModel is provided by the ViewModelFactory, which is called here.
*/
class ProfileDisplayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //ViewModel
        val viewModel : ProfileDisplayViewmodel by viewModels {
            InjectorUtils.provideProfileDisplayViewModelFactory(requireContext())
        }
        //SafeArgs
        val userID = arguments?.let { ProfileDisplayFragmentArgs.fromBundle(it).userID }
        viewModel.setUserByID(userID!!)


        // init binding variable
        val binding = ProfileDisplayFragmentBinding.inflate(inflater, container, false)
        binding.profileDisplayViewmodel = viewModel
        binding.profileDisplayRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.lifecycleOwner = this

        val feedAdapter = ProfileDisplayAdapter(viewModel, requireContext())

        //Adapter
        binding.profileDisplayRecyclerView.adapter = feedAdapter
             // set observer
        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                feedAdapter.recipes = items}
        }

        // set observables
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
            binding.profileDisplayRecyclerView.setHasFixedSize(true)

        viewModel.user.observe(this.viewLifecycleOwner, Observer { user ->
            binding.textViewProfileDisplayDescription.text = user.description
            binding.textViewProfileDisplayFragmentTitle.text = user.userId


            val imageView = binding.imageView2
            var urlString = user.imgUrl
            if(urlString == "" || urlString.isEmpty()){
                urlString = "file:///android_asset/exampleimages/chef_avatar.png"
            }
            context?.let { Glide.with(it).load(urlString).into(imageView) }
        })

        binding.buttonProfileDisplayFragmentEditProfile.setOnClickListener{
            val navController = findNavController()
            navController.navigate(ProfileDisplayFragmentDirections.actionProfileDisplayFragmentToProfileEditFragment().setUserID(userID))
        }

        binding.buttonProfileDisplayFragmentLogout.setOnClickListener{
            AuthentificationImpl.logout()
            InjectorUtils.setToken(null)
            val navController = findNavController()
            navController.navigate(R.id.action_profileDisplayFragment_to_loginFragment)
        }

        binding.buttonProfileDisplayFragmentFlagUser.setOnClickListener{
            viewModel.flagUserById()
            Toast.makeText(requireContext(), "Profil gemeldet", Toast.LENGTH_SHORT).show()
            val navController = findNavController()
            navController.navigate(R.id.action_profileDisplayFragment_to_publicRecipeSearchFragment)
        }

        viewModel.user.observe(viewLifecycleOwner, Observer {
            user ->
            binding.textViewProfileDisplayDescription.text = user.description
            binding.textViewProfileDisplayFragmentTitle.text = user.userId
        })

        return binding.root
    }


}
