package de.psekochbuch.exzellenzkoch.userinterfacelayer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import de.psekochbuch.exzellenzkoch.R
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.DisplaySearchListViewmodel

class DisplaySearchListFragment: Fragment(), View.OnClickListener {


    var navController:NavController? = null
    var displaySearchListViewmodel:DisplaySearchListViewmodel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.public_recipe_search_fragment, container, false)

        displaySearchListViewmodel  = ViewModelProviders.of(this).get(DisplaySearchListViewmodel::class.java)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.search_button).setOnClickListener(this)
    }
    override fun onClick(v:View?){
        when(v!!.id){
            R.id.search_button -> navController!!.navigate(R.id.RecipeSearchListfragment_to_recipeDisplayfragment)
        }
    }
}