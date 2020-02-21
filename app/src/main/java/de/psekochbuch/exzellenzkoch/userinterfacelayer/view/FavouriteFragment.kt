package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.FavouriteListFragmentBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.FavouriteAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FavouriteViewmodel

class FavouriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FavouriteListFragmentBinding.inflate(inflater, container, false)

        val viewModel : FavouriteViewmodel by viewModels {
            InjectorUtils.provideFavouriteViewModelFactory(requireContext())

        }
        binding.recyclerViewFavourites.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val favouriteAdapter = FavouriteAdapter(viewModel, requireContext())

        binding.recyclerViewFavourites.adapter = favouriteAdapter

        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                favouriteAdapter.favouriteRecipes = items}
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewFavourites.setHasFixedSize(true)
        return binding.root
    }
}