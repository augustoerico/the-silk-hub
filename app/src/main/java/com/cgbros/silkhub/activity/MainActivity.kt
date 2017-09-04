package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cgbros.silkhub.R
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AuthenticatedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        LoggedInUser
                .getInstance { user: User ->
                    if (user.currentSession != "") {
                        Toast.makeText(
                                this, "IT HAS A SESSION", Toast.LENGTH_SHORT
                        ).show()
                        // TODO disable create new session or join another session
                    }

                    main_current_session_button.setOnClickListener { currentSession() }
                    main_profile_button.setOnClickListener { profile() }
                    main_create_session_button.setOnClickListener { createSession() }
                    main_search_session_button.setOnClickListener { searchSession() }
                }
    }

    private fun currentSession() {
        Log.d("HOME", "currentSession")
        startActivity(Intent(this, CurrentSessionActivity::class.java))
    }

    private fun profile() {
        Log.d("HOME", "profile")
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun createSession() {
        Log.d("HOME", "createSession")
        startActivity(Intent(this, CreateSessionActivity::class.java))
    }

    private fun searchSession() {
        Log.d("Home", "searchSession")
        startActivity(Intent(this, SearchSessionActivity::class.java))
    }
}
