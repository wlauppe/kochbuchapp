package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

class ProfileDisplayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //ViewModel
        val viewModel : ProfileDisplayViewmodel by viewModels {
            InjectorUtils.provideProfileDisplayViewModelFactory(requireContext())
        }
        //SafeArge--------------------------------
        val userID = arguments?.let { ProfileDisplayFragmentArgs.fromBundle(it).userID }
        viewModel.setUserByID(userID!!)
        // TODO überflüssiger toast
        Toast.makeText(requireContext(), userID.toString(), Toast.LENGTH_SHORT).show()

        val binding = ProfileDisplayFragmentBinding.inflate(inflater, container, false)
        binding.profileDisplayViewmodel = viewModel
        // init binding variable
        binding.profileDisplayRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        // set input values to show them in the xml

        val feedAdapter = ProfileDisplayAdapter(viewModel, requireContext())

        //Adapter--------------------------------------------
        binding.profileDisplayRecyclerView.adapter = feedAdapter
             // set observer
        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                feedAdapter.recipes = items}
        }

        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
            binding.profileDisplayRecyclerView.setHasFixedSize(true)

        //Glide------------------------------------------------
        binding.textViewProfileDisplayDescription.text = viewModel.userDesc.toString()

        binding.textViewProfileDisplayFragmentTitle.text = viewModel.userID.toString()

        viewModel.user.observe(this, Observer { user ->
            viewModel.userDesc.postValue(user.description)
            viewModel.userID.postValue(user.userId)


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
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToProfileDisplayFragment())
        }

        binding.buttonProfileDisplayFragmentFlagUser.setOnClickListener{
            viewModel.flagUserById()
            Toast.makeText(requireContext(), "Profil gemeldet", Toast.LENGTH_SHORT).show()
            val navController = findNavController()
            navController.navigate(R.id.action_profileDisplayFragment_to_publicRecipeSearchFragment)
        }

        return binding.root
    }


}