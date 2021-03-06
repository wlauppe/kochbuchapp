package de.psekochbuch.exzellenzkoch.datalayer.remote.service

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

/**
 * This class is for the authentification with Firebase
 *
 * @constructor Create a firebaseinstance
 */
object AuthentificationImpl
//TODO("Implementierung sollte irgendwann das Interface implementieren")


{
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()


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

                val user = auth.currentUser
                if (user != null && userId != "") {

                    user.updateProfile(
                        UserProfileChangeRequest.Builder().setDisplayName(
                            userId
                        ).build()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            auth.currentUser?.getIdToken(false)?.addOnCompleteListener { token ->

                                if (token.isSuccessful) {
                                    callback(
                                        token.result?.token,
                                        AuthenticationResult.REGISTRATIONSUCCESS
                                    )
                                } else {
                                    callback(null, AuthenticationResult.REGISTRATIONSUCCESS)
                                }
                            }

                        } else {
                            callback("", AuthenticationResult.USERIDNOTSET)
                        }
                    }
                } else {
                    auth.currentUser?.getIdToken(false)?.addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            callback(
                                it.result?.token,
                                AuthenticationResult.REGISTRATIONSUCCESS
                            )
                        } else
                        {
                            callback(null, AuthenticationResult.REGISTRATIONSUCCESS)
                        }
                    }

                }


            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                //if(task.exception.)
                callback(null, AuthenticationResult.REGISTRATIONFAILED)
            }
        }
    }

    fun isLogIn() :Boolean
    {
        val user = FirebaseAuth.getInstance().currentUser
        return user != null
    }


    /**
     * Give the JWT-token of the active user
     *
     * @param callback Return the token
     */
    fun getToken(fresh:Boolean,callback: (String?) -> Unit) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if(user != null) {
            try {
                user.getIdToken(fresh).addOnCompleteListener {
                    if(it.isSuccessful) {
                        callback(it.result?.token)
                    } else {
                        callback("")
                    }
                }
            } catch (ex : FirebaseNetworkException ){
                ex.printStackTrace()
                callback("")
            }
        } else {
            callback("")
        }
    }

    fun authWithCustomToken(token: String, callback: () -> Unit) {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.signInWithCustomToken(token).addOnCompleteListener {
            if (it.isSuccessful) {
                callback()
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
    fun login(email: String, password: String, callback: (String?, AuthenticationResult) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithEmail:success")
                auth.currentUser?.getIdToken(false)?.addOnCompleteListener {
                    callback(it.result?.token, AuthenticationResult.LOGINSUCCESS)
                }
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                callback(null, AuthenticationResult.LOGINFAILED)
            }
        }
    }

    /**
     * Logout the User at Firebase
     */
    fun logout() {
        auth.signOut()
    }

    /**
     * Edit the password of the user in Firebase
     *
     * @param [pw] The new password of the user
     * @param callback return true if it was successful
     */
    fun pwEdit(pw: String, callback: (Boolean?) -> Unit) {
        auth.currentUser?.updatePassword(pw)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "changePW:success")
                callback(true)
            } else {
                Log.w(TAG, "changePW:failure", task.exception)
                callback(false)
            }
        }
    }

    /**
     * Get userId from the authenticated user
     * @return the userId of the user
     */
    fun getUserId() :String
    {
        return auth.currentUser?.displayName ?: ""
    }

    /**
     * Edit the userid of the user
     *
     * @param [userId] The new userid of the user
     */
    fun userIdEdit(userId: String, activity: Activity) {

    }

    /**
     * Delete the active user in Firebase
     *
     */
    fun userDelete() {
        auth.currentUser?.delete()
    }

}