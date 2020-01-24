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
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.EditTagViewmodel

class EditTagFragment: Fragment(){

    var navController:NavController? = null
    var editTagViewmodel:EditTagViewmodel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.edit_tag_fragment, container, false)

        editTagViewmodel = ViewModelProviders.of(this).get(EditTagViewmodel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

    }



}