package com.cgbros.silkhub.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cgbros.silkhub.R
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser == null) {
            Log.d("MAIN", "User not signed in")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }

        LoggedInUser
                .getInstance { user: User ->
                    if (user.currentSession != "") {
                        Toast.makeText(
                                this, "IT HAS A SESSION", Toast.LENGTH_LONG
                        ).show()
                    }

                    Toast.makeText(this, user.toStringMap().toString(), Toast.LENGTH_LONG).show()

                    main_current_session_button.setOnClickListener { currentSession() }
                    main_profile_button.setOnClickListener { profile() }
                    main_create_session_button.setOnClickListener { createSession() }
                }
    }

    private fun currentSession() {
        Log.d("HOME", "currentSession")
    }

    private fun profile() {
        Log.d("HOME", "profile")
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        return
    }

    private fun createSession() {
        Log.d("HOME", "createSession")
        val intent = Intent(this, CreateSessionActivity::class.java)
        startActivity(intent)
        return
    }
}
