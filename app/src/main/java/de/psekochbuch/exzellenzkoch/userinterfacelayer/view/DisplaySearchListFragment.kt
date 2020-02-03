package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.DisplaySearchListAdaper
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(DisplaySearchListViewmodel::class.java)

        binding.recyclerViewSearchlistFragment.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        var listOfRecipeNames : List<String> = viewModel.items.value!!

        val exampleAdapter = DisplaySearchListAdaper(listOfRecipeNames,viewModel)
        binding.recyclerViewSearchlistFragment.adapter = exampleAdapter

        val observer = Observer<List<String>> { items ->
            exampleAdapter.setNewItems(items)
        }

        viewModel.items.observe(this.viewLifecycleOwner, observer)

        binding.recyclerViewSearchlistFragment.setHasFixedSize(true)
        return binding.root
    }


}