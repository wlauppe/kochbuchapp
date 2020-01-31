package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import de.psekochbuch.exzellenzkoch.databinding.DisplaySearchlistFragmentBinding
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListFragment: Fragment() {
    private lateinit var navController: NavController



    var displaySearchListViewmodel:DisplaySearchListViewmodel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DisplaySearchlistFragmentBinding.inflate(inflater, container, false)
        var navController = findNavController()

        displaySearchListViewmodel  = ViewModelProvider(this).get(DisplaySearchListViewmodel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }
   /* override fun onClick(v:View?){
        when(v!!.id){
            R.id.search_button -> navController!!.navigate(R.id.RecipeSearchListfragment_to_recipeDisplayfragment)
        }
    }

    */
}