package de.psekochbuch.exzellenzkoch

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

/**
 * This class is for the authentification with Firebase
 *
 * @constructor Erzeugt eine Firebaseinstanz
 */
class Authentification
{
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     *Registrate a new User at Firebase and at the backendserver
     *
     * @param [userId] The Id of the User
     * @param [email] The email of the User
     * @param [password] The password of the User
     * @param [activity] The Aktivity which the method execute
     */
    fun registrate(userId:String, email:String, password:String, activity: Activity)
    {
        auth.createUserWithEmailAndPassword(email,password)

    }

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

    /**
     * Logout the User at Firebase
     */
    fun logout()
    {
        auth.signOut()
    }

    /**
     * Edit the password of the user in Firebase
     *
     * @param [pw] The new password of the user
     */
    fun pwEdit(pw:String)
    {
        auth.currentUser?.updatePassword(pw)?.addOnCompleteListener { task ->
            if(task.isSuccessful)
            {

            }
        }
    }

    /**
     * Edit the userid of the user
     *
     * @param [userId] The new userid of the user
     */
    fun userIdEdit(userId: String, activity: Activity)
    {

    }

    /**
     * Delete the active user in Firebase and backendserver
     *
     *
     */
    fun userDelete(activity: Activity)
    {

    }

    /**
     * Give the JWT-token of the active user
     */
    fun getToken(activity: Activity): String
    {
        return ""
    }

    private fun saveToken()
    {

    }
}