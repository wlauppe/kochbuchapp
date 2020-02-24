package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.databinding.FeedBinding
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.userinterfacelayer.adapter.FeedAdapter
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.FeedViewModel

/**
* The Fragment class provides logic for binding the respective .xml layout file to the class
* and calls functions from the underlying ViewModel.
* The ViewModel is provided by the ViewModelFactory, which is called here.
*/
class FeedFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FeedBinding.inflate(inflater, container, false)

        val viewModel : FeedViewModel by viewModels {
            InjectorUtils.provideFeedViewModelFactory(requireContext())
        }
        binding.recyclerViewFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val feedAdapter = FeedAdapter(viewModel, requireContext())

        binding.recyclerViewFeed.adapter = feedAdapter

        val observer = Observer<List<PublicRecipe>> { items ->
            items?.let {
                feedAdapter.feedRecipes = items}
        }
        viewModel.recipes.observe(this.viewLifecycleOwner, observer)

        val pageObserver = Observer<Int> {items ->
            items?.let {
                feedAdapter.pageIndex = items
            }
        }
        viewModel.pageIndex.observe(this.viewLifecycleOwner, pageObserver)

        binding.recyclerViewFeed.setHasFixedSize(true)
        // set empty header and footer spacing
        // binding.recyclerViewFeed.addItemDecoration(HeaderFooterDecoration(100, 100))

        return binding.root
    }

    /**
     * Nested class provides logic for an empty header and footer in the RecyclerView, if needed
     *
     * @param headerHeight defines the header's height in pixels
     * @param footerHeight defines the footer's height in pixels
     */
    class HeaderFooterDecoration(private val headerHeight: Int, private val footerHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val adapter = parent.adapter ?: return
            when (parent.getChildAdapterPosition(view)) {
                0 -> outRect.top = headerHeight
                adapter.itemCount - 1 -> outRect.bottom = footerHeight
                else -> outRect.set(0, 0, 0, 0)
            }
        }
    }
}