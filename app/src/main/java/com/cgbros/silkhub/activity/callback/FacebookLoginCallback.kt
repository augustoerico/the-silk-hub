package com.cgbros.silkhub.activity.callback

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.cgbros.silkhub.activity.MainActivity
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class FacebookLoginCallback(val activity: Activity) : FacebookCallback<LoginResult> {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onSuccess(result: LoginResult?) {
        Log.d("login", "Facebook login success")
        handleAccessToken(result?.accessToken)
    }

    override fun onCancel() {
        Log.d("login", "Facebook login canceled")
    }

    override fun onError(error: FacebookException?) {
        Log.e("login", "Error on FacebookLoginCallback", error)
        // TODO send user some feedback
    }

    private fun handleAccessToken(accessToken: AccessToken?) {

        val token: String = accessToken?.token ?: ""
        Log.d("login", "Handle Facebook access accessToken [$token]")

        if (token.isNotEmpty()) {
            val credentials = FacebookAuthProvider.getCredential(token)
            mAuth.signInWithCredential(credentials)
                    .addOnCompleteListener { authResult ->
                        Log.d("login", "On complete listener")
                        if (authResult.isSuccessful) {
                            Log.d("login", "... is successful")
                            val result = authResult.result
                            val user = result.user

                            Log.d("login", "user: $user")

                            val intent = Intent(activity, MainActivity::class.java)
                            activity.startActivity(intent)
                        } else {
                            Log.e("login", "Error on complete listener",
                                    authResult.exception)
                            // TODO send user some feedback
                        }
                    }
        } else {
            val message = "Empty access token"
            Log.e("login", message, Exception(message))
            // TODO send user some feedback
        }

    }
}
