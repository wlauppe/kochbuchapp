package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.PAGE_SIZE
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
    private var isLoading: Boolean = false
    private lateinit var feedAdapter : FeedAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var classViewModel: FeedViewModel
    private lateinit var classBinding: FeedBinding

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
                feedAdapter.feedRecipes = items}
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        binding.recyclerViewFeed.setHasFixedSize(true)

        // call the getPage
        getPage()

        // set scroll listener for pagination
        binding.recyclerViewFeed.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               if (dy > 0) {
                    val visibleItemCount = layoutManager.itemCount
                    val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                    val recyclerViewTotalSize = feedAdapter.itemCount

                Log.i("", "1pagenr ist: " + viewModel.pageNumber)
                    if (!isLoading) {
                        if (visibleItemCount + pastVisibleItem >= recyclerViewTotalSize) {
                            getPage()
                            Log.i("", "2pagenr ist: " + viewModel.pageNumber)
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        binding.recyclerViewFeed.setHasFixedSize(true)
        return binding.root
    }

    /**
     * Method to get the currently shown page of the RecyclerView and load the next one if needed
     */
    private fun getPage() {
        Log.i("", "getPage pagenr ist: " + classViewModel.pageNumber)

        isLoading = true
        classBinding.feedProgressBar.visibility = View.VISIBLE
        val start = (classViewModel.pageNumber - 1) * pageLimit
        val end = classViewModel.pageNumber * pageLimit
        for (i in start .. end) {
            feedAdapter.notifyDataSetChanged()
        }
        Handler().postDelayed({
            if (::feedAdapter.isInitialized) {
                Log.i("", "2getPage pagenr ist: " + classViewModel.pageNumber)

                classViewModel.pageNumber++
                classViewModel.loadNextPage()
                Log.i("", "3getPage pagenr ist: " + classViewModel.pageNumber)

                feedAdapter.notifyDataSetChanged()
            } else {
                feedAdapter = FeedAdapter(classViewModel, requireContext())
            }
            isLoading = false;
            classBinding.feedProgressBar.visibility = View.GONE
        }, 5000)
    }
}