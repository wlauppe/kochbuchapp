package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.UserSearchViewmodel

/**
 * The Fragment class provides logic for binding the respective .xml layout file to the class
 * and calls functions from the underlying ViewModel.
 * The ViewModel is provided by the ViewModelFactory, which is called here.
 * The UserSearchFragment should display the User Search which will be defined in an xml file
 * similar to the Recipe Search
 */
class UserSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ViewModel
        val viewModel : UserSearchViewmodel by viewModels {
            InjectorUtils.provideSearchWithTagsViewModelFactory(requireContext())
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}