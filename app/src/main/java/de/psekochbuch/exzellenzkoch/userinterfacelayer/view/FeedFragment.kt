package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.PAGE_SIZE
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.databinding.FeedBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.FeedAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.progress_view.*

/**
* The Fragment class provides logic for binding the respective .xml layout file to the class
* and calls functions from the underlying ViewModel.
* The ViewModel is provided by the ViewModelFactory, which is called here.
*/
class FeedFragment : Fragment() {

    /**
     * Global variables used for pagination in RecyclerView
     */
    private var pageLimit = PAGE_SIZE
   // private var isLoading: Boolean = false
    private lateinit var feedAdapter : FeedAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var classViewModel: FeedViewModel
    private lateinit var classBinding: FeedBinding
    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FeedBinding.inflate(inflater, container, false)
        classBinding = binding
        val viewModel : FeedViewModel by viewModels {
            InjectorUtils.provideFeedViewModelFactory(requireContext())
        }
        classViewModel = viewModel

        // init the global feedAdapter and layoutManager
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewFeed.layoutManager = layoutManager
        feedAdapter = FeedAdapter(viewModel, requireContext())
        binding.recyclerViewFeed.adapter = feedAdapter

        // observe the recipe list
        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                feedAdapter.feedRecipes.addAll(items)
                feedAdapter.notifyDataSetChanged()}
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        viewModel.loadPage(1)
        page++

        binding.recyclerViewFeed.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                if(!viewModel.isLoading && !viewModel.isLastPage) {
                    if((visibleItemCount + firstVisibleItem) >= totalItemCount && firstVisibleItem >= 0 && totalItemCount >= PAGE_SIZE) {
                        viewModel.loadPage(page)
                        page++
                        viewModel.isLoading = true
                    }
                }



            }
        })


        binding.recyclerViewFeed.setHasFixedSize(true)

        binding.recyclerViewFeed.setHasFixedSize(true)

        return binding.root
    }
}