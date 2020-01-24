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
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

class ProfileDisplayFragment: Fragment(), View.OnClickListener {
    var navController:NavController? = null

    var profileDisplayViewmodel:ProfileDisplayViewmodel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.profil_display_fragment, container, false)

        profileDisplayViewmodel = ViewModelProviders.of(this).get(ProfileDisplayViewmodel::class.java)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.goto_recipe_button).setOnClickListener(this)
    }
    override fun onClick(v:View?){
        when(v!!.id){
            R.id.goto_recipe_button -> navController!!.navigate(R.id.profile_display_fragment_to_recipe_display_fragment)
        }
    }


}