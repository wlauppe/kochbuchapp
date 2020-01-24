package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewmodel(application: Application) : ViewModel() {



    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

     var email:LiveData<String>? = null

     var password:LiveData<String>? = null



    /**
     * Login the User at Firebase
     *
     * @param [email] The email of the User
     * @param [password] The password of the User
     * @param [activity] The Aktivity which the method execute
     *
     */
    fun login(email: String, password: String,activity: Activity)
    {

    }
}