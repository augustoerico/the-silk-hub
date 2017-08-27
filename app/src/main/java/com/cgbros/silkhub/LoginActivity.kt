package com.cgbros.silkhub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val openSessionsRef: DatabaseReference = database.getReference("open_sessions")
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val that = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()

        // Show open sessions
        openSessionsRef.addValueEventListener(MyListener())

    }

    inner class MyListener : ValueEventListener {

        override fun onCancelled(error: DatabaseError?) {
            val message = error?.message ?: "Cancelled"
            Toast.makeText(that, message, Toast.LENGTH_SHORT).show()
        }

        override fun onDataChange(snapshot: DataSnapshot?) {
            val count = snapshot?.children?.count() ?: 0

            val text = "$count open sessions"
            login_open_sessions.text = text
        }

    }

    inner class FacebookLoginCallback : FacebookCallback<LoginResult> {

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

        private fun handleAccessToken(accessToken: AccessToken?): Unit {

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

//                                val intent: Intent = Intent(that, MainActivity::class.java)
//                                startActivity(intent)
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

}
