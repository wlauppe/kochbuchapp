package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.SearchWithTagsViewModel

class SearchWithTagsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ViewModel
        val viewModel : SearchWithTagsViewModel by viewModels {
            InjectorUtils.provideSearchWithTagsViewModelFactory(requireContext())
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}