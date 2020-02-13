package de.psekochbuch.exzellenzkoch.datalayer.remote.service

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import de.psekochbuch.exzellenzkoch.userinterfacelayer.AuthenticationResult

/**
 * This class is for the authentification with Firebase
 *
 * @constructor Create a firebaseinstance
 */
class AuthentificationImpl
//TODO("Implementierung sollte irgendwann das Interface implementieren")


{
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    companion object {
        /**
         *Registrate a new User at Firebase and at the backendserver
         *
         * @param [email] The email of the User
         * @param [password] The password of the User
         * @param callback Return the token
         */
        fun register(
            email: String,
            password: String,
            userId: String,
            callback: (String?, AuthenticationResult) -> Unit
        ) {
            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    auth.currentUser?.getIdToken(false)?.addOnCompleteListener { token ->
                        val user = auth.currentUser
                        if (user != null && userId != "") {
                            user.updateProfile(
                                UserProfileChangeRequest.Builder().setDisplayName(
                                    userId
                                ).build()
                            ).addOnSuccessListener {
                                callback(
                                    token.result?.token,
                                    AuthenticationResult.REGISTRATIONSUCCESS
                                )
                            }
                        } else {
                            callback(
                                token.result?.token,
                                AuthenticationResult.REGISTRATIONSUCCESS
                            )
                        }
                    }
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    //if(task.exception.)
                    callback(null, AuthenticationResult.REGISTRATIONFAILED)
                }

            }
        }

        /**
         * Give the JWT-token of the active user
         *
         * @param callback Return the token
         */
        fun getToken(callback: (String?) -> Unit) {
            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            auth.currentUser?.getIdToken(false)?.addOnCompleteListener {
                callback(it.result?.token)
            }
        }

        fun authWithCustomToken(token:String, callback: () -> Unit)
        {
            val auth: FirebaseAuth = FirebaseAuth.getInstance()
            auth.signInWithCustomToken(token).addOnCompleteListener {
                if(it.isSuccessful)
                {
                    callback()
                }
            }
        }
    }

    /**
     * Login the User at Firebase
     *
     * @param [email] The email of the User
     * @param [password] The password of the User
     * @param callback Return value is the token
     *
     */
    fun login(email: String, password: String, callback: (String?) -> Unit)
    {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                auth.currentUser?.getIdToken(false)?.addOnCompleteListener {
                    callback(it.result?.token)
                }
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                callback(null)
            }
        }
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
     * @param callback return true if it was successful
     */
    fun pwEdit(pw:String, callback: (Boolean?) -> Unit)
    {
        auth.currentUser?.updatePassword(pw)?.addOnCompleteListener { task ->
            if(task.isSuccessful)
            {
                Log.d(TAG, "changePW:success")
                callback(true)
            } else {
                Log.w(TAG, "changePW:failure", task.exception)
                callback(false)
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
     */
    fun userDelete()
    {
        auth.currentUser?.delete()
    }


}