package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.psekochbuch.exzellenzkoch.databinding.AdminFragmentBinding
import de.psekochbuch.exzellenzkoch.databinding.FeedBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminRecipeAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.AdminUserAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.FeedAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel

class Feed : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FeedBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        binding.recyclerViewFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        var listOfRecipeNames : List<PublicRecipe> = viewModel.recipes.value!!


        val exampleAdapter = FeedAdapter(listOfRecipeNames,viewModel)


        binding.recyclerViewFeed.adapter = exampleAdapter



        val observer = Observer<List<PublicRecipe>> { items ->
            exampleAdapter.setNewItems(items)
        }

        viewModel.recipes.observe(this.viewLifecycleOwner, observer)
        binding.recyclerViewFeed.setHasFixedSize(true)

        /*
//Safeargs werden hier aus dem Bundel gezogem
        var title = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).recipeTitleToDisplay }
        var tags = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).tags }
        var ingredients = arguments?.let { DisplaySearchListFragmentArgs.fromBundle(it).ingredients }
        Toast.makeText(requireContext(), title.toString() + ingredients.toString() + tags.toString(), Toast.LENGTH_SHORT).show()
         */
        return binding.root
    }
}